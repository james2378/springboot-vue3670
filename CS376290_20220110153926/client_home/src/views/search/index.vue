<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="校园资讯"
				source_table="article"
			  ></list_result_search>
				<!-- 论坛搜索结果 -->
			  <list_result_search
				:list="result_forum"
				title="课程讨论"
				source_table="forum"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_student_number"
				title="学生学号"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_student_name"
				title="学生学生姓名"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_college"
				title="学生学院"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_class_name"
				title="学生班级名称"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_major"
				title="学生专业"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/teacher/list', 'get')"
				:list="result_teacher_teacher_job_number"
				title="教师教师工号"
				source_table="teacher"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/teacher/list', 'get')"
				:list="result_teacher_teacher_name"
				title="教师教师姓名"
				source_table="teacher"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/course_management/list', 'get')"
				:list="result_course_management_course_name"
				title="课程管理课程名称"
				source_table="course_management"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/data_classification/list', 'get')"
				:list="result_data_classification_data_type"
				title="资料分类资料类型"
				source_table="data_classification"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/course_materials/list', 'get')"
				:list="result_course_materials_course_name"
				title="课程资料课程名称"
				source_table="course_materials"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/course_materials/list', 'get')"
				:list="result_course_materials_data_name"
				title="课程资料资料名称"
				source_table="course_materials"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/course_materials/list', 'get')"
				:list="result_course_materials_data_type"
				title="课程资料资料类型"
				source_table="course_materials"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/learning_statistics/list', 'get')"
				:list="result_learning_statistics_class_name"
				title="学习统计班级名称"
				source_table="learning_statistics"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
	  "result_forum": [],
			"result_student_student_number":[],
			"result_student_student_name":[],
			"result_student_college":[],
			"result_student_class_name":[],
			"result_student_major":[],
			"result_teacher_teacher_job_number":[],
			"result_teacher_teacher_name":[],
			"result_course_management_course_name":[],
			"result_data_classification_data_type":[],
			"result_course_materials_course_name":[],
			"result_course_materials_data_name":[],
			"result_course_materials_data_type":[],
			"result_learning_statistics_class_name":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取课程讨论
	 */
	get_forum() {
	  this.$get("~/api/forum/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_forum = json.result.list;
		}
	  });
	},
	/**
	 * 获取student_number
	 */
	get_student_student_number(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "student_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_student_number = json.result.list;
			result_student_student_number.map(o => o.title = o['student_number'])
	  			this.result_student_student_number = result_student_student_number
		 	}
		});
	},
	/**
	 * 获取student_name
	 */
	get_student_student_name(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "student_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_student_name = json.result.list;
			result_student_student_name.map(o => o.title = o['student_name'])
	  			this.result_student_student_name = result_student_student_name
		 	}
		});
	},
	/**
	 * 获取college
	 */
	get_student_college(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "college": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_college = json.result.list;
			result_student_college.map(o => o.title = o['college'])
	  			this.result_student_college = result_student_college
		 	}
		});
	},
	/**
	 * 获取class_name
	 */
	get_student_class_name(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "class_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_class_name = json.result.list;
			result_student_class_name.map(o => o.title = o['class_name'])
	  			this.result_student_class_name = result_student_class_name
		 	}
		});
	},
	/**
	 * 获取major
	 */
	get_student_major(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "major": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_major = json.result.list;
			result_student_major.map(o => o.title = o['major'])
	  			this.result_student_major = result_student_major
		 	}
		});
	},
	/**
	 * 获取teacher_job_number
	 */
	get_teacher_teacher_job_number(){
		this.$get("~/api/teacher/get_list?like=0", { page: 1, size: 10, "teacher_job_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_teacher_teacher_job_number = json.result.list;
			result_teacher_teacher_job_number.map(o => o.title = o['teacher_job_number'])
	  			this.result_teacher_teacher_job_number = result_teacher_teacher_job_number
		 	}
		});
	},
	/**
	 * 获取teacher_name
	 */
	get_teacher_teacher_name(){
		this.$get("~/api/teacher/get_list?like=0", { page: 1, size: 10, "teacher_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_teacher_teacher_name = json.result.list;
			result_teacher_teacher_name.map(o => o.title = o['teacher_name'])
	  			this.result_teacher_teacher_name = result_teacher_teacher_name
		 	}
		});
	},
	/**
	 * 获取course_name
	 */
	get_course_management_course_name(){
		this.$get("~/api/course_management/get_list?like=0", { page: 1, size: 10, "course_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_course_management_course_name = json.result.list;
			result_course_management_course_name.map(o => o.title = o['course_name'])
	  			this.result_course_management_course_name = result_course_management_course_name
		 	}
		});
	},
	/**
	 * 获取data_type
	 */
	get_data_classification_data_type(){
		this.$get("~/api/data_classification/get_list?like=0", { page: 1, size: 10, "data_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_data_classification_data_type = json.result.list;
			result_data_classification_data_type.map(o => o.title = o['data_type'])
	  			this.result_data_classification_data_type = result_data_classification_data_type
		 	}
		});
	},
	/**
	 * 获取course_name
	 */
	get_course_materials_course_name(){
		this.$get("~/api/course_materials/get_list?like=0", { page: 1, size: 10, "course_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_course_materials_course_name = json.result.list;
			result_course_materials_course_name.map(o => o.title = o['course_name'])
	  			this.result_course_materials_course_name = result_course_materials_course_name
		 	}
		});
	},
	/**
	 * 获取data_name
	 */
	get_course_materials_data_name(){
		this.$get("~/api/course_materials/get_list?like=0", { page: 1, size: 10, "data_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_course_materials_data_name = json.result.list;
			result_course_materials_data_name.map(o => o.title = o['data_name'])
	  			this.result_course_materials_data_name = result_course_materials_data_name
		 	}
		});
	},
	/**
	 * 获取data_type
	 */
	get_course_materials_data_type(){
		this.$get("~/api/course_materials/get_list?like=0", { page: 1, size: 10, "data_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_course_materials_data_type = json.result.list;
			result_course_materials_data_type.map(o => o.title = o['data_type'])
	  			this.result_course_materials_data_type = result_course_materials_data_type
		 	}
		});
	},
	/**
	 * 获取class_name
	 */
	get_learning_statistics_class_name(){
		this.$get("~/api/learning_statistics/get_list?like=0", { page: 1, size: 10, "class_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_learning_statistics_class_name = json.result.list;
			result_learning_statistics_class_name.map(o => o.title = o['class_name'])
	  			this.result_learning_statistics_class_name = result_learning_statistics_class_name
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
	this.get_forum();
		this.get_student_student_number();
		this.get_student_student_name();
		this.get_student_college();
		this.get_student_class_name();
		this.get_student_major();
		this.get_teacher_teacher_job_number();
		this.get_teacher_teacher_name();
		this.get_course_management_course_name();
		this.get_data_classification_data_type();
		this.get_course_materials_course_name();
		this.get_course_materials_data_name();
		this.get_course_materials_data_type();
		this.get_learning_statistics_class_name();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_forum();
	  this.get_student_student_number();
	  this.get_student_student_name();
	  this.get_student_college();
	  this.get_student_class_name();
	  this.get_student_major();
	  this.get_teacher_teacher_job_number();
	  this.get_teacher_teacher_name();
	  this.get_course_management_course_name();
	  this.get_data_classification_data_type();
	  this.get_course_materials_course_name();
	  this.get_course_materials_data_name();
	  this.get_course_materials_data_type();
	  this.get_learning_statistics_class_name();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
