import pandas as pd
from imblearn.under_sampling import TomekLinks
from sklearn.metrics import silhouette_score
from sklearn.preprocessing import LabelEncoder

# Load data from Excel
df = pd.read_excel('balanced_data_smote_earlylate.xlsx')

# Assuming the last column is the target variable
X = df.drop(columns=['酮病次数'])
y = df['酮病次数']


# Encode target labels if they are categorical
le = LabelEncoder()
y_encoded = le.fit_transform(y)

# Apply Tomek Links
tl = TomekLinks()
X_res, y_res = tl.fit_resample(X, y_encoded)

# Get the indices of the Tomek Links
removed_indices = set(range(len(y))) - set(tl.sample_indices_)
print("Indices of Tomek Links pairs:", removed_indices)

# Remove samples with indices equal to removed_indices
cleaned_df = df.drop(index=removed_indices)

silhouette_avg = silhouette_score(df, y)
print(silhouette_avg)
# Save the cleaned data to another Excel file
cleaned_df.to_excel('balanced_data_tomeklinks_earlylate.xlsx', index=False)
