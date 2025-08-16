import pandas as pd
import numpy as np
from sklearn.neighbors import NearestNeighbors
from imblearn.over_sampling import SMOTE
import time


def load_data(filename):
    """
    从 Excel 文件中加载数据
    :param filename: Excel 文件路径
    :return: 加载的数据 DataFrame
    """
    return pd.read_excel(filename)


def save_data(df, filename):
    """
    将 DataFrame 保存到 Excel 文件
    :param df: 要保存的数据 DataFrame
    :param filename: 保存的 Excel 文件路径
    """
    df.to_excel(filename, index=False)


def calculate_loyalty(X, y, k=9):
    """
    计算每个样本的忠诚度
    :param X: 特征矩阵
    :param y: 标签向量
    :param k: 最近邻数量
    :return: 忠诚度数组
    """
    # 动态调整 k 值
    k = min(k, len(X))
    if k < 1:
        return np.array([])
    nbrs = NearestNeighbors(n_neighbors=k).fit(X)
    distances, indices = nbrs.kneighbors(X)
    loyalties = np.zeros(len(X))
    epsilon = np.finfo(float).eps  # 避免除零的小值

    for i in range(len(X)):
        for j in range(1, k):
            if y[i] == y[indices[i][j]]:
                loyalties[i] += 1 / (distances[i][j] + epsilon)
            else:
                loyalties[i] -= 1 / (distances[i][j] + epsilon)
    return loyalties


def calculate_lof(X, indices, distances, k):
    """
    计算每个样本的局部离群因子（LOF）
    :param X: 特征矩阵
    :param indices: 最近邻索引矩阵
    :param distances: 最近邻距离矩阵
    :param k: 最近邻数量
    :return: LOF 字典
    """
    lrd = {}
    lof = {}
    for i in range(len(X)):
        reachability_distances = []
        for j in range(1, k):
            neighbor_index = indices[i][j]
            k_distance_neighbor = distances[neighbor_index][-1]
            reachability_dist = max(k_distance_neighbor, distances[i][j])
            reachability_distances.append(reachability_dist)
        lrd[i] = k / np.sum(reachability_distances)

    for i in range(len(X)):
        lof_sum = 0
        for j in range(1, k):
            neighbor_index = indices[i][j]
            lof_sum += lrd[neighbor_index] / lrd[i]
        lof[i] = lof_sum / (k - 1)
    return lof


def calculate_attraction(X, y, k=9):
    """
    计算每个样本对不同类别的吸引力
    :param X: 特征矩阵
    :param y: 标签向量
    :param k: 最近邻数量
    :return: 吸引力矩阵，形状为 (样本数, 类别数)
    """
    unique_classes = np.unique(y)
    num_classes = len(unique_classes)
    nbrs = NearestNeighbors(n_neighbors=k).fit(X)
    distances, indices = nbrs.kneighbors(X)
    attractions = np.zeros((len(X), num_classes))
    epsilon = np.finfo(float).eps  # 避免除零的小值

    for i in range(len(X)):
        for c in range(num_classes):
            class_samples = indices[i][y[indices[i]] == unique_classes[c]]
            for j in class_samples:
                attractions[i, c] += 1 / (distances[i][np.where(indices[i] == j)[0][0]] + epsilon)
    return attractions


def main(m=0.5):
    start_time = time.time()

    df = load_data("E:\论文2数据集\G4\yeast\yeast.xlsx")
    X = df.drop('Label', axis=1).values
    y = df['Label'].values

    # 输出初始数据集各类别样本数量
    class_counts = np.bincount(y)
    print("初始数据集各类别样本数量：", class_counts)

    # 计算吸引力
    attractions = calculate_attraction(X, y, k=1)
    # 根据吸引力重新分配类别
    y = np.argmax(attractions, axis=1)

    # 输出重新分配类别后数据集各类别样本数量
    class_counts = np.bincount(y)
    print("重新分配类别后数据集各类别样本数量：", class_counts)

    # 计算重新分配类别后的忠诚度
    loyalties = calculate_loyalty(X, y, k=1)
    # 找出边界样本的索引
    boundary_indices = np.where((loyalties >= 0) & (loyalties <= m))[0]

    # 统计各类别边界样本的数量
    for c in np.unique(y):
        boundary_count = np.sum(y[boundary_indices] == c)
        print(f"类别 {c} 边界样本数量为: {boundary_count}")

    # 提取边界样本的特征和标签
    X_boundary = X[boundary_indices]
    y_boundary = y[boundary_indices]

    # 根据边界样本中存在的类别设置采样策略
    unique_classes_boundary = np.unique(y_boundary)
    target_counts = {c: np.max(class_counts) for c in unique_classes_boundary}

    # 检查边界样本中的类别数量
    if len(unique_classes_boundary) < 2:
        print("边界样本中只有一个类别，对所有少数类数据进行过采样。")
        sampling_strategy = target_counts
        smote = SMOTE(k_neighbors=3, sampling_strategy=sampling_strategy)
        X, y = smote.fit_resample(X, y)
    else:
        # 仅对边界样本使用 SMOTE 算法进行过采样
        sampling_strategy = target_counts
        smote = SMOTE(k_neighbors=3, sampling_strategy=sampling_strategy)
        X_boundary_resampled, y_boundary_resampled = smote.fit_resample(X_boundary, y_boundary)

        # 合并过采样后的边界样本和未过采样的其他样本
        other_indices = np.setdiff1d(np.arange(len(X)), boundary_indices)
        X_other = X[other_indices]
        y_other = y[other_indices]
        X = np.vstack([X_other, X_boundary_resampled])
        y = np.hstack([y_other, y_boundary_resampled])

    # 输出过采样后数据集各类别样本数量
    class_counts = np.bincount(y)
    print("过采样后数据集各类别样本数量：", class_counts)

    # 计算过采样后所有样本的忠诚度
    loyalties = calculate_loyalty(X, y)
    # 找出初始不忠诚样本的索引
    initial_unloyalty_indices = np.where(loyalties < 0)[0]

    # 检查是否有初始不忠诚样本
    if len(initial_unloyalty_indices) > 0:
        # 仅对初始不忠诚样本重新计算忠诚度和 LOF
        X_unloyalty = X[initial_unloyalty_indices]
        y_unloyalty = y[initial_unloyalty_indices]
        new_loyalties_unloyalty = calculate_loyalty(X_unloyalty, y_unloyalty)
        # 动态调整 k 值
        k = min(5, len(X_unloyalty))
        if k < 1:
            lof_unloyalty = {}
        else:
            nbrs = NearestNeighbors(n_neighbors=k).fit(X_unloyalty)
            distances, indices = nbrs.kneighbors(X_unloyalty)
            lof_unloyalty = calculate_lof(X_unloyalty, indices, distances, k)

        # 根据条件删除样本
        to_remove = []
        for local_idx, global_idx in enumerate(initial_unloyalty_indices):
            if (new_loyalties_unloyalty[local_idx] < 0) or (lof_unloyalty.get(local_idx, 0) > 1):
                to_remove.append(global_idx)
        X = np.delete(X, to_remove, axis=0)
        y = np.delete(y, to_remove, axis=0)
    else:
        print("初始数据中没有不忠诚样本，跳过后续删除操作。")

    # 输出删除样本后数据集各类别样本数量
    class_counts = np.bincount(y)
    print("删除样本后数据集各类别样本数量：", class_counts)

    df_result = pd.DataFrame(np.hstack([X, y.reshape(-1, 1)]), columns=list(df.columns))
    save_data(df_result, "E:\论文2数据集\G4\yeast\loyaltysmote.xlsx")

    end_time = time.time()
    execution_time = end_time - start_time
    print(f"Execution time: {execution_time} seconds")


if __name__ == "__main__":
    # 可以修改平衡率
    main()
