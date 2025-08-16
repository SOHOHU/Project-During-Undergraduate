import pandas as pd
from imblearn.over_sampling import SMOTE

data = pd.read_excel('RF-early.xlsx')

# 将数据分为属性和标签
X = data.drop(columns=['酮病次数'])
y = data['酮病次数']

# 初始化SMOTE模型，设置近邻参数K=1000
smote = SMOTE(k_neighbors=13, random_state=42, sampling_strategy={1: 4*734})
X_resampled, y_resampled = smote.fit_resample(X, y)

# 打印过采样后的样本数量
print("过采样后的样本数量：", len(X_resampled))

# 可选：将过采样后的数据保存到新的Excel文件中
resampled_data = pd.concat([pd.DataFrame(X_resampled), pd.DataFrame(y_resampled)], axis=1)
resampled_data.columns = data.columns
resampled_data.to_excel('balanced_data_smote_earlylate.xlsx', index=False)
