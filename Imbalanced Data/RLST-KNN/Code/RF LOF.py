import pandas as pd
import numpy as np
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_squared_error
from sklearn.neighbors import LocalOutlierFactor

# 读取Excel数据
data = pd.read_excel('early_late.xlsx')

# 将缺失值用--表示的值替换为NaN
data.replace('--', np.nan, inplace=True)

# 1. 删除缺失率大于50%的行
missing_rate = data.isnull().mean(axis=1)
data = data[missing_rate <= 0.5]

# 2. 确定缺失值的标识符
missing_code = -1  # 选择一个不在数据范围内的整数作为缺失值标识符

# 3. 计算缺失值的比例
missing_proportion = data.isnull().mean().mean()

# 4. 选择预填充方案
if missing_proportion < 0.2:
    # MissQuick方法：使用众数或中位数进行预填充
    for column in data.columns:
        if data[column].isnull().any():
            if data[column].dtype == 'object':
                mode_value = data[column].mode()[0]
                data[column].fillna(mode_value, inplace=True)
            else:
                median_value = data[column].median()
                data[column].fillna(median_value, inplace=True)
else:
    # MissRight方法：基于邻近样本的迭代预填充
    for _ in range(10):  # 迭代10次
        for column in data.columns:
            if data[column].isnull().any():
                data[column].fillna(data[column].interpolate(), inplace=True)

# 5. 构建RF模型并更新
rf_model = RandomForestRegressor(n_estimators=100, oob_score=True, random_state=42)
X = data.dropna()  # 去掉缺失值行
y = X.pop('酮病次数')  # 假设'酮病次数'是目标变量

# 训练初始模型
rf_model.fit(X, y)

# 计算OOB误差
initial_oob_error = 1 - rf_model.oob_score_

# 迭代更新模型
for iteration in range(10):
    # 预测缺失值
    missing_indices = data[data.isnull().any(axis=1)].index
    if len(missing_indices) > 0:
        predictions = rf_model.predict(data.loc[missing_indices].drop('酮病次数', axis=1))
        data.loc[missing_indices, '酮病次数'] = predictions

    # 清理异常值
    lof = LocalOutlierFactor(n_neighbors=10)
    outliers = lof.fit_predict(data.drop('酮病次数', axis=1))
    data = data[outliers != -1]

    # 重新训练模型
    X = data.drop('酮病次数', axis=1)
    y = data['酮病次数']
    rf_model.fit(X, y)

    # 计算新的OOB误差
    new_oob_error = 1 - rf_model.oob_score_
    if abs(initial_oob_error - new_oob_error) < 0.0001:
        break
    initial_oob_error = new_oob_error

# 结果保存到Excel
data.to_excel('RF-early.xlsx', index=False)