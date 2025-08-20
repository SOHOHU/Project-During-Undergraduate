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
          v-hasPermi="['stdainvest:stdainvest:add']"
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
          v-hasPermi="['stdainvest:stdainvest:edit']"
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
          v-hasPermi="['stdainvest:stdainvest:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['stdainvest:stdainvest:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stdainvestList" @selection-change="handleSelectionChange">
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
<!--      ------------------------------------>
      <el-table-column label="有害生物类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pest_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="有害生物名称" align="center" prop="name" />
      <el-table-column label="鉴定状态" align="center" prop="identifyStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.identify_state" :value="scope.row.identifyStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="鉴定人姓名" align="center" prop="identifyUname" />
      <el-table-column label="鉴定时间" align="center" prop="identifyTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.identifyTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
<!--      ------------------------------------->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['stdainvest:stdainvest:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['stdainvest:stdainvest:remove']"
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

    <!-- 添加或修改标准地调查对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="调查图片" prop="image">
          <image-upload v-model="form.image"/>
        </el-form-item>
        <el-form-item label="调查人" prop="surveyUname">
          <el-input v-model="form.surveyUname" placeholder="请输入普查人员姓名" />
        </el-form-item>
        <el-form-item label="行政区域" prop="administrativeRegion">
          <el-input v-model="form.administrativeRegion" placeholder="请输入行政区域" />
        </el-form-item>
        <el-form-item label="调查方法" prop="investigationMethod">
          <el-input v-model="form.investigationMethod" placeholder="请输入调查方法" />
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
import { listStdainvest, getStdainvest, delStdainvest, addStdainvest, updateStdainvest } from "@/api/stdainvest/stdainvest";

export default {
  name: "Stdainvest",
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
      // 标准地调查表格数据
      stdainvestList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        surveyUname: null,
        administrativeRegion: null,
        investigationMethod: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        image: [
          { required: true, message: "调查图片不能为空", trigger: "blur" }
        ],
        surveyUname: [
          { required: true, message: "普查人员姓名不能为空", trigger: "blur" }
        ],
        administrativeRegion: [
          { required: true, message: "行政区域不能为空", trigger: "blur" }
        ],
        investigationMethod: [
          { required: true, message: "调查方法不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询标准地调查列表 */
    getList() {
      this.loading = true;
      listStdainvest(this.queryParams).then(response => {
        this.stdainvestList = response.rows;
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
        image: null,
        collectTime: null,
        surveyUid: null,
        surveyUname: null,
        administrativeRegion: null,
        investigationMethod: null
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
      this.title = "添加标准地调查";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStdainvest(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改标准地调查";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateStdainvest(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStdainvest(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除标准地调查编号为"' + ids + '"的数据项？').then(function() {
        return delStdainvest(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('stdainvest/stdainvest/export', {
        ...this.queryParams
      }, `stdainvest_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
