import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import accuracy_score, recall_score, f1_score, roc_auc_score, roc_curve, confusion_matrix
import matplotlib.pyplot as plt

# 从Excel导入数据
data = pd.read_excel('balanced_data_tomeklinks_earlylate.xlsx')
# 假设数据的特征列为X，标签列为y，需要替换成你的实际列名
X = data.drop('酮病次数', axis=1)
y = data['酮病次数']

# 划分训练集和测试集
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=42)

# 创建并训练KNN模型
knn = KNeighborsClassifier(n_neighbors=5)  # 选择K值
knn.fit(X_train, y_train)

# 预测测试集
y_pred = knn.predict(X_test)

# 计算精确度
accuracy = accuracy_score(y_test, y_pred)

# 计算召回率
recall = recall_score(y_test, y_pred)

# 计算F1得分
f1 = f1_score(y_test, y_pred)

# 计算AUC
y_prob = knn.predict_proba(X_test)[:, 1]  # 获取正类的预测概率
auc = roc_auc_score(y_test, y_prob)

# 计算混淆矩阵
tn, fp, fn, tp = confusion_matrix(y_test, y_pred).ravel()

# 计算其他指标
sensitivity = tp / (tp + fn)  # 灵敏度
specificity = tn / (tn + fp)  # 特异性
ppv = tp / (tp + fp)  # 阳性预测值
npv = tn / (tn + fn)  # 阴性预测值

# 计算ROC曲线
fpr, tpr, thresholds = roc_curve(y_test, y_prob)

# 绘制ROC曲线（橙色）
plt.plot(fpr, tpr, color='orange', label='ROC Curve')
plt.plot([0, 1], [0, 1], color='navy', lw=2, linestyle='--')
plt.xlabel('False Positive Rate')
plt.ylabel('True Positive Rate')
plt.title('Raw Datasets')
plt.legend()
plt.show()

# 输出评估结果
print("Accuracy:", accuracy)
print("F1 Score:", f1)
print("Sensitivity:", sensitivity)
print("Specificity:", specificity)
print("PPV:", ppv)
print("NPV:", npv)
print("AUC:", auc)