<template>
	<div class="diy_list page_course_materials" id="course_materials_list">
		<div class="warp">
			<div class="container diy_list_container">
				<div class="diy_list_title">
					<div class="col">
						<span class="title">课程资料列表</span>
					</div>
				</div>
				<div class="row diy_list_search">
					<div class="col">
						<!-- 搜索栏 -->
						<div class="view">							
							<span class="diy_list_search_title">关键字搜索：</span>
							<b-form-input size="sm" class="mr-sm-2" placeholder="课程名称搜索" v-model="query['course_name']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="任课教师搜索" v-model="query['teacher']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="资料名称搜索" v-model="query['data_name']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="资料类型搜索" v-model="query['data_type']" />
							<b-button size="sm" @click="search()" >
								<b-icon icon="search"/>
							</b-button>
						</div>
						<!-- /搜索栏 -->
					</div>
				</div>
				<div class="diy_list_select_box">
					<span class="diy_list_select_title">下拉搜索：</span>
						<div class="diy_list_dropdown_box">
						<div class="col">
							<!-- 筛选 -->
							<div class="view">
								<b-dropdown text="任课教师" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','teacher')">全部</b-dropdown-item>
									<b-dropdown-item v-for="o in list_teacher" :key="o['user_id']" @click="filter_set(o['user_id'],'teacher')">
										{{ o['nickname'] + '-' + o['username'] }}
									</b-dropdown-item>
								</b-dropdown>
								<b-dropdown text="资料类型" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','data_type')">全部</b-dropdown-item>
										<b-dropdown-item v-for="(o, i) in list_data_type" :key="i" @click="filter_set(o['data_type'],'data_type')" >
												{{ o['data_type'] }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!-- /筛选 -->
						</div>
					</div>
					<div class="diy_list_sort_box">
						<div class="col">
							<!-- 排序 -->
							<div class="view">
								<b-dropdown text="排序" variant="outline-dark" left>
										<b-dropdown-item v-for="(o, i) in list_sort" :key="i" @click="set_sort(o)" >
												{{ o.name }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!--/排序 -->
						</div>
					</div>
				</div>
				<div class="row diy_list_box">
					<div class="col">
						<!-- 列表 -->
						<list_course_materials :list="list" />
						<!-- /列表 -->
					</div>
				</div>
				<div class="row diy_list_page_box">
					<div class="col overflow-auto flex_cc">
						<!-- 分页器 -->
						<diy_pager v-model="query['page']" :size="query['size']" :count="count" v-on:toPage="toPage" v-on:toSize="toSize" ></diy_pager>
						<!-- /分页器 -->
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import list_course_materials from "@/components/diy/list_course_materials.vue";
	import diy_pager from "@/components/diy/diy_pager";
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		components: {
			diy_pager,
			list_course_materials
		},
		data() {
			return {
				url_get_list: "~/api/course_materials/get_list?like=0",

				// 查询条件
				query: {
					keyword: "",
					page: 1,
					size: 10,
					"course_name": "", // 课程名称
					"teacher": 0, // 任课教师
					"data_name": "", // 资料名称
					"data_type": "", // 资料类型
				},

				// 排序内容
				list_sort: [{
						name: "创建时间从高到低",
						value: "`create_time` desc",
					},
					{
						name: "创建时间从低到高",
						value: "`create_time` asc",
					},
					{
						name: "更新时间从高到低",
						value: "`update_time` desc",
					},
					{
						name: "更新时间从低到高",
						value: "`update_time` asc",
					},
					{
						name: "课程名称正序",
						value: "`course_name` asc",
					},
					{
						name: "课程名称倒序",
						value: "`course_name` desc",
					},
					{
						name: "任课教师正序",
						value: "`teacher` asc",
					},
					{
						name: "任课教师倒序",
						value: "`teacher` desc",
					},
					{
						name: "资料名称正序",
						value: "`data_name` asc",
					},
					{
						name: "资料名称倒序",
						value: "`data_name` desc",
					},
					{
						name: "资料类型正序",
						value: "`data_type` asc",
					},
					{
						name: "资料类型倒序",
						value: "`data_type` desc",
					},
				],
				list_teacher: [],
				// 资料类型列表
				"list_data_type": [],
			}
		},
		methods: {
			/**
			 * 筛选选择
			 */
			filter_set(o,key) {
			    if (o == "全部") {
			        this.query[key] = "";
			    } else {
			        this.query[key] = o;
			    }
			    this.search();
			},

			/**
			 * 排序
			 */
			set_sort(o) {
			    this.query.orderby = o.value;
			    this.search();
			},
			/**
			 * 获取资料类型列表
			 */
			async get_list_data_type() {
				var json = await this.$get("~/api/data_classification/get_list?");
				if (json.result) {
					this.list_data_type = json.result.list;
				} else if (json.error) {
					console.log(json.error);
				}
			},

			/**
			 * 筛选
			 */
			/**
			 * 获取任课教师列表
			 */
			async get_list_teacher() {
				var json = await this.$get("~/api/user/get_list?user_group=教师");
				if(json.result && json.result.list){
					this.list_teacher = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			filter_data_type(o) {
				if (o == "全部") {
					this.query["data_type"] = "";
				} else {
					this.query["data_type"] = o;
				}
				this.search();
			},

			/**
			 * 重置
			 */
			reset() {
				this.query.course_name = ""
				this.query.teacher = ""
				this.query.data_name = ""
				this.query.data_type = ""
				this.search();
			},

			// 返回条数
			toSize(i){
				this.query.size = i;
				this.first();
			},

			// 返回页数
			toPage(i){
				this.query.page = i;
				this.first();
			},
		},
		computed: {
		},
		created() {
			this.get_list_teacher();
			/**
			 * 获取资料类型列表
			 */
			this.get_list_data_type();
		}
	}
</script>

<style>
</style>
