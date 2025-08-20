<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="普查人员姓名" prop="surveyUname">
        <el-input
          v-model="queryParams.surveyUname"
          placeholder="请输入普查人员姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="行政区域" prop="administrativeRegion">
        <el-input
          v-model="queryParams.administrativeRegion"
          placeholder="请输入行政区域"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="调查方法" prop="investigationMethod">
        <el-input
          v-model="queryParams.investigationMethod"
          placeholder="请输入调查方法"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="有害生物类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择有害生物类型" clearable>
          <el-option
            v-for="dict in dict.type.pest_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="有害生物名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入有害生物名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="鉴定人姓名" prop="identifyUname">
        <el-input
          v-model="queryParams.identifyUname"
          placeholder="请输入鉴定人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="鉴定状态" prop="identifyStatus">
        <el-select v-model="queryParams.identifyStatus" placeholder="请选择鉴定状态" clearable>
          <el-option
            v-for="dict in dict.type.identify_state"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['stdaidentify:stdaidentify:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['stdaidentify:stdaidentify:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['stdaidentify:stdaidentify:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['stdaidentify:stdaidentify:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stdaidentifyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="调查ID" align="center" prop="id" />
      <el-table-column label="调查图片" align="center" prop="image" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.image" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="采集时间" align="center" prop="collectTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.collectTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="普查人员姓名" align="center" prop="surveyUname" />
      <el-table-column label="行政区域" align="center" prop="administrativeRegion" />
      <el-table-column label="调查方法" align="center" prop="investigationMethod" />
      <el-table-column label="有害生物类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pest_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="有害生物名称" align="center" prop="name" />
      <el-table-column label="鉴定人姓名" align="center" prop="identifyUname" />
      <el-table-column label="鉴定状态" align="center" prop="identifyStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.identify_state" :value="scope.row.identifyStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="鉴定时间" align="center" prop="identifyTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.identifyTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['stdaidentify:stdaidentify:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['stdaidentify:stdaidentify:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改标准地鉴定对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="生物类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择有害生物类型">
            <el-option
              v-for="dict in dict.type.pest_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="生物名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入有害生物名称" />
        </el-form-item>
        <el-form-item label="鉴定人" prop="identifyUname">
          <el-input v-model="form.identifyUname" placeholder="请输入鉴定人姓名" />
        </el-form-item>
        <el-form-item label="鉴定状态" prop="identifyStatus">
          <el-select v-model="form.identifyStatus" placeholder="请选择鉴定状态">
            <el-option
              v-for="dict in dict.type.identify_state"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listStdaidentify, getStdaidentify, delStdaidentify, addStdaidentify, updateStdaidentify } from "@/api/stdaidentify/stdaidentify";

export default {
  name: "Stdaidentify",
  dicts: ['pest_type', 'identify_state'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 标准地鉴定表格数据
      stdaidentifyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: null,
        name: null,
        identifyUname: null,
        identifyStatus: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        type: [
          { required: true, message: "有害生物类型不能为空", trigger: "change" }
        ],
        name: [
          { required: true, message: "有害生物名称不能为空", trigger: "blur" }
        ],
        identifyUname: [
          { required: true, message: "鉴定人姓名不能为空", trigger: "blur" }
        ],
        identifyStatus: [
          { required: true, message: "鉴定状态不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询标准地鉴定列表 */
    getList() {
      this.loading = true;
      listStdaidentify(this.queryParams).then(response => {
        this.stdaidentifyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        type: null,
        name: null,
        identifyUid: null,
        identifyUname: null,
        identifyStatus: null,
        identifyTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加标准地鉴定";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStdaidentify(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改标准地鉴定";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateStdaidentify(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStdaidentify(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除标准地鉴定编号为"' + ids + '"的数据项？').then(function() {
        return delStdaidentify(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('stdaidentify/stdaidentify/export', {
        ...this.queryParams
      }, `stdaidentify_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
