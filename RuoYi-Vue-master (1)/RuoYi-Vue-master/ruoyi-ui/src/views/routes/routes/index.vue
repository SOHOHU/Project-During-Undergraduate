<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="行政区域" prop="administrativeRegion">
        <el-input
          v-model="queryParams.administrativeRegion"
          placeholder="请输入行政区域"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="踏查点数量" prop="pointsNum">
        <el-input
          v-model="queryParams.pointsNum"
          placeholder="请输入踏查点数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请人" prop="uname">
        <el-input
          v-model="queryParams.uname"
          placeholder="请输入申请用户姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审批状态" prop="applicationStatus">
        <el-select v-model="queryParams.applicationStatus" placeholder="请选择审批状态" clearable>
          <el-option
            v-for="dict in dict.type.apply_state"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="审批人" prop="approvalUname">
        <el-input
          v-model="queryParams.approvalUname"
          placeholder="请输入审批人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['routes:routes:add']"
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
          v-hasPermi="['routes:routes:edit']"
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
          v-hasPermi="['routes:routes:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['routes:routes:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="routesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="路线ID" align="center" prop="id" />
      <el-table-column label="行政区域" align="center" prop="administrativeRegion" />
      <el-table-column label="审批状态" align="center" prop="applicationStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.apply_state" :value="scope.row.applicationStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="踏查进度" align="center" prop="progress" />
      <el-table-column label="踏查点数量" align="center" prop="pointsNum" />
      <el-table-column label="申请人" align="center" prop="uname" />
      <el-table-column label="申请时间" align="center" prop="applicationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.applicationTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审批人" align="center" prop="approvalUname" />
      <el-table-column label="审批时间" align="center" prop="approvalTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.approvalTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['routes:routes:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['routes:routes:remove']"
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

    <!-- 添加或修改踏查路线对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="行政区域" prop="administrativeRegion">
          <el-input v-model="form.administrativeRegion" placeholder="请输入行政区域" />
        </el-form-item>
        <el-form-item label="踏查点数量" prop="pointsNum">
          <el-input v-model="form.pointsNum" placeholder="请输入踏查点数量" />
        </el-form-item>
        <el-form-item label="申请人" prop="uname">
          <el-input v-model="form.uname" placeholder="请输入申请用户姓名" />
        </el-form-item>
        <el-divider content-position="center">踏查点信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddSurveypoints">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteSurveypoints">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="surveypointsList" :row-class-name="rowSurveypointsIndex" @selection-change="handleSurveypointsSelectionChange" ref="surveypoints">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="详细地点" prop="detailLocation" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.detailLocation" placeholder="请输入详细地点" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRoutes, getRoutes, delRoutes, addRoutes, updateRoutes } from "@/api/routes/routes";

export default {
  name: "Routes",
  dicts: ['apply_state'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedSurveypoints: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 踏查路线表格数据
      routesList: [],
      // 踏查点表格数据
      surveypointsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        administrativeRegion: null,
        pointsNum: null,
        uname: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        administrativeRegion: [
          { required: true, message: "行政区域不能为空", trigger: "blur" }
        ],
        uname: [
          { required: true, message: "申请用户姓名不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询踏查路线列表 */
    getList() {
      this.loading = true;
      listRoutes(this.queryParams).then(response => {
        this.routesList = response.rows;
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
        administrativeRegion: null,
        pointsNum: null,
        progress: null,
        uid: null,
        uname: null,
        applicationTime: null
      };
      this.surveypointsList = [];
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
      this.title = "添加踏查路线";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRoutes(id).then(response => {
        this.form = response.data;
        this.surveypointsList = response.data.surveypointsList;
        this.open = true;
        this.title = "修改踏查路线";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.surveypointsList = this.surveypointsList;
          if (this.form.id != null) {
            updateRoutes(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRoutes(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除踏查路线编号为"' + ids + '"的数据项？').then(function() {
        return delRoutes(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 踏查点序号 */
    rowSurveypointsIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 踏查点添加按钮操作 */
    handleAddSurveypoints() {
      let obj = {};
      obj.detailLocation = "";
      this.surveypointsList.push(obj);
    },
    /** 踏查点删除按钮操作 */
    handleDeleteSurveypoints() {
      if (this.checkedSurveypoints.length == 0) {
        this.$modal.msgError("请先选择要删除的踏查点数据");
      } else {
        const surveypointsList = this.surveypointsList;
        const checkedSurveypoints = this.checkedSurveypoints;
        this.surveypointsList = surveypointsList.filter(function(item) {
          return checkedSurveypoints.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleSurveypointsSelectionChange(selection) {
      this.checkedSurveypoints = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('routes/routes/export', {
        ...this.queryParams
      }, `routes_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
