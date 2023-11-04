new Vue({
    el: '#app',
    data() {
        return {
            fileList: [],
            ccorder:Date.now(),
            rcdynamicValidateForm: {
                rcdetil: [{
                    pnumber: '',
                    pname: '',
                    color: '',
                    costprice: '',
                    costcount: '',
                    rccount: '',
                    type: '',
                    bz: '',
                    gys: '',
                    sshg: '',
                    qcs: '0',
                    rcdate: ''
                }]
            },//入仓动态
            ccdynamicValidateForm: {
                customeraddress:'',
                customername:'',
                customerphone:'',
                ccdate: '',
                orderid:'',
                rcdetil: [{
                    pnumber: '',
                    pname: '',
                    color: '',
                    ccprice: '',//单价
                    cccount: '',//出库数量
                    ccsumprice:'',//出库总价
                    type: '',
                    bz: '',
                    gys: '',
                    sshg: '',
                }]
            },//出仓动态
            examinedlog:false,
            loading: true,
            rcdlog:false,
            ccdlog:false,
            tableData: [],//货柜列表
            tableData2: [],//入库列表
            tableData3:[],
            tableData4: [],//进销存列表
            tableData5: [],//货柜关联货物表格
            currentPage: 1, //初始页
            pagesize: 10,    //    每页的数据
            search: '',
            search2:'',
            search3:'',
            search4:'',
            search5:'',
            currentPagerc: 1, //初始页
            pagesizerc: 10,    //    每页的数据
            searchrc: '',
            currentPagecc: 1, //初始页
            pagesizecc: 10,    //    每页的数据
            currentPagejxc: 1, //初始页
            pagesizejxc: 10,    //    每页的数据
            currentPagegl: 1, //初始页
            pagesizegl: 10,    //    每页的数据
            searchcc: '',
            form:{
                banknumber:'',
                gnumber:'',
                phone:''
            },
            rcform:{
                rcval1:'',
                rcval2:'',
                pnumber:'',
                sshg:'',
            },
            ccform:{
                rcval1:'',
                rcval2:'',
                customerphone:'',
                orderid:''
            },
            jxcform:{
                rcval1:'',
                rcval2:'',
                customerphone:'',
                pnumber:''
            },
            rcd: false,
            ccd: false,
            addkun: false,
            editkun:false,
            addForm: {
                gysfs:'',
                id:'',
                gnumber:'',
                gdbfs:'',
                phone:'',
                gsize:'',
                bankname:'',
                cgdate:'',
                status:'',
                dgdate:'',
                banknumber:'',
                content:'',
                type:'',
                count:'',
                price:''
            },
            editForm:{
                id:'',
                gysfs:'',
                dgdate:'',
                gnumber:'',
                gdbfs:'',
                phone:'',
                gsize:'',
                bankname:'',
                cgdate:'',
                status:'',
                banknumber:'',
                content:'',
                type:'',
                count:'',
                price:''
            }
        }
    },

    //初始化
    mounted: function () {
        this.list();
    },
    //方法事件
    methods: {
        addCol(){
            this.cols.push({prop: 'name', label: '地址'})
        },
        //退出
        out() {
            var url = '/login/out';
            $.ajax({
                type: 'POST',
                url: url,
                dataType: 'text',
                success: function (res) {
                    if (res=="success") {
                        window.location.href=urls+"/login";
                        //window.location.href=urls+"/kunyueye/index";
                    }
                },
                error: function () {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        //进销存列表导出
        exportExceljxc() {
            let _$this = this;
            var f = _$this.ccform;
            // 创建一个 form
            var form1 = document.createElement("form");
            form1.orderid = f.orderid;
            form1.customerphone = f.customerphone;
            form1.rcval1 = f.rcval1;
            form1.rcval2 = f.rcval2;

            document.body.appendChild(form1);
            // 创建一个输入
            var input = document.createElement("input");
            // 设置相应参数
            input.type = "text";
            input.name = "orderid";
            input.value = f.orderid;
            var input1 = document.createElement("input");
            // 设置相应参数
            input1.type = "text";
            input1.name = "customerphone";
            input1.value = f.customerphone;
            // 创建一个输入
            var input2 = document.createElement("input");
            // 设置相应参数
            input2.type = "text";
            input2.name = "start";
            input2.value = f.rcval1;
            var input3 = document.createElement("input");
            // 设置相应参数
            input3.type = "text";
            input3.name = "end";
            input3.value = f.rcval2;
            // 将该输入框插入到 form 中
            form1.appendChild(input);
            form1.appendChild(input1);
            form1.appendChild(input2);
            form1.appendChild(input3);
            // form 的提交方式
            form1.method = "POST";
            // form 提交路径
            form1.action = "/excel/ccdc";
            // 对该 form 执行提交
            //暂时注释掉 避免测试导出 form1.submit();
            // 删除该 form
            document.body.removeChild(form1);

        },
        //库存列表导出
        exportExcelcc() {
            let _$this = this;
            var f = _$this.ccform;
            // 创建一个 form
            var form1 = document.createElement("form");
            form1.orderid = f.orderid;
            form1.customerphone = f.customerphone;
            form1.rcval1 = f.rcval1;
            form1.rcval2 = f.rcval2;

            document.body.appendChild(form1);
            // 创建一个输入
            var input = document.createElement("input");
            // 设置相应参数
            input.type = "text";
            input.name = "orderid";
            input.value = f.orderid;
            var input1 = document.createElement("input");
            // 设置相应参数
            input1.type = "text";
            input1.name = "customerphone";
            input1.value = f.customerphone;
            // 创建一个输入
            var input2 = document.createElement("input");
            // 设置相应参数
            input2.type = "text";
            input2.name = "start";
            input2.value = f.rcval1;
            var input3 = document.createElement("input");
            // 设置相应参数
            input3.type = "text";
            input3.name = "end";
            input3.value = f.rcval2;
            // 将该输入框插入到 form 中
            form1.appendChild(input);
            form1.appendChild(input1);
            form1.appendChild(input2);
            form1.appendChild(input3);
            // form 的提交方式
            form1.method = "POST";
            // form 提交路径
            form1.action = "/excel/ccdc";
            // 对该 form 执行提交
            form1.submit();
            // 删除该 form
            document.body.removeChild(form1);

        },
        //库存列表导出
        exportExcelrc() {
            let _$this = this;
            var f = _$this.rcform;
            // 创建一个 form
            var form1 = document.createElement("form");
            form1.pnumber = f.pnumber;
            form1.sshg = f.sshg;
            form1.rcval1 = f.rcval1;
            form1.rcval2 = f.rcval2;
            document.body.appendChild(form1);
            // 创建一个输入
            var input = document.createElement("input");
            // 设置相应参数
            input.type = "text";
            input.name = "pnumber";
            input.value = f.pnumber;
            var input2 = document.createElement("input");
            // 设置相应参数
            input2.type = "text";
            input2.name = "sshg";
            input2.value = f.sshg;
            // 创建一个输入
            var input3 = document.createElement("input");
            // 设置相应参数
            input3.type = "text";
            input3.name = "start";
            input3.value = f.rcval1;
            var input4 = document.createElement("input");
            // 设置相应参数
            input4.type = "text";
            input4.name = "end";
            input4.value = f.rcval2;
            // 将该输入框插入到 form 中
            form1.appendChild(input);
            form1.appendChild(input2);
            form1.appendChild(input3);
            form1.appendChild(input4);
            // form 的提交方式
            form1.method = "POST";
            // form 提交路径
            form1.action = "/excel/rcdc";
            // 对该 form 执行提交
            form1.submit();
            // 删除该 form
            document.body.removeChild(form1);

        },
        //货柜信息导出
        exportExcel() {
            let _$this = this;
            var f = _$this.form;
            // 创建一个 form
            var form1 = document.createElement("form");
            form1.gnumber = f.gnumber;
            form1.phone = f.phone;
            form1.banknumber = f.banknumber;
            document.body.appendChild(form1);
            // 创建一个输入
            var input = document.createElement("input");
            // 设置相应参数
            input.type = "text";
            input.name = "gnumber";
            input.value = f.gnumber;
            // 创建一个输入
            var input2 = document.createElement("input");
            // 设置相应参数
            input2.type = "text";
            input2.name = "phone";
            input2.value = f.phone;
            var input3 = document.createElement("input");
            // 设置相应参数
            input3.type = "text";
            input3.name = "banknumber";
            input3.value = f.banknumber;
            // 将该输入框插入到 form 中
            form1.appendChild(input);
            form1.appendChild(input2);
            form1.appendChild(input3);
            // form 的提交方式
            form1.method = "POST";
            // form 提交路径
            form1.action = "/excel/dc";
            // 对该 form 执行提交
            form1.submit();
            // 删除该 form
            document.body.removeChild(form1);

        },
        //上传服务
        submitUpload() {
            let newthis = this;
            //判断files数组的长度是否大于0，不大于0 则未选择附件
            if (this.$refs.upload.uploadFiles.length == 0) {
                newthis.$message({
                    message: '请选择需要上传的文件',
                    type: 'error'
                });
                newthis.up = true;
                return false;
            }
            var name = this.$refs.upload.uploadFiles[0].name;
            var index = name.lastIndexOf(".")
            var res = name.substring(index, name.length);
            if (res!=".xlsx" && res!=".xls" ) {
                newthis.$message({
                    message: '该文件非Excel文件,或后缀非xlsx',
                    type: 'error'
                });
                return false;
            }
            this.$refs.upload.submit();
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },
        //清空数据
        qk(){
            let newth = this;
            this.$confirm('确定清空数据?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                var url = '/container/cleanTable';
                $.ajax({
                    type: 'POST',
                    url: url,
                    dataType: 'json',
                    success: function (result) {
                        var lean = newth.tableData.length;
                        newth.tableData.splice(null, lean);
                            newth.$message({
                                message: '已清空',
                                type: 'success'
                            });
                    },
                    error: function () {

                    }
                });
            }).catch(() => {
                newth.$notify.error({
                    title: '取消',
                    message: '取消！'
                });
            });
        },
        //修改数据
        edit(){
            var editForm = this.editForm;
            var newthis = this;
            if (editForm.phone=="" || editForm.phone==null) {
                newthis.$message.error('手机号不能为空');
                return;
            }
            if (editForm.gysfs=="" || editForm.gysfs==null) {
                newthis.$message.error('运输方式不能为空');
                return;
            }
            if (editForm.gnumber=="" || editForm.gnumber==null) {
                newthis.$message.error('货柜号不能为空');
                return;
            }
            if (editForm.gdbfs=="" || editForm.gdbfs==null) {
                newthis.$message.error('打包方式不能为空');
                return;
            }
            if (editForm.bankname=="" || editForm.bankname==null) {
                newthis.$message.error('所属银行不能为空');
                return;
            }
            if (editForm.cgdate=="" || editForm.cgdate==null) {
                newthis.$message.error('出港时间不能为空');
                return;
            }
            if (editForm.banknumber=="" || editForm.banknumber==null) {
                newthis.$message.error('银行卡号不能为空');
                return;
            }
            if (editForm.content=="" || editForm.content==null) {
                newthis.$message.error('货柜内容不能为空');
                return;
            }
            if (editForm.type=="" || editForm.type==null) {
                newthis.$message.error('货柜类型不能为空');
                return;
            }
            if (editForm.count=="" || editForm.count==null) {
                newthis.$message.error('数量（件数）不能为空');
                return;
            }
            if (editForm.price=="" || editForm.price==null) {
                newthis.$message.error('货值不能为空');
                return;
            }
           var  cgdate=  this.updateformatDate(editForm.cgdate);
           var  dgdate=  this.updateformatDate(editForm.dgdate);
            var m = {
                'id': editForm.id,
                'phone': editForm.phone,
                'gysfs': editForm.gysfs,
                'gnumber': editForm.gnumber,
                'gdbfs': editForm.gdbfs,
                'gsize': editForm.gsize,
                'bankname': editForm.bankname,
                'cgdate': cgdate,
                'banknumber': editForm.banknumber,
                'content': editForm.content,
                'dgdate': dgdate,
                'type': editForm.type,
                'status': editForm.status,
                'count': editForm.count,
                'dls': editForm.dls,
                'price': editForm.price
            };
            var url = '/container/updates';
            $.ajax({
                type: 'POST',
                url: url,
                data: m,
                dataType: 'json',
                success: function (result) {
                    if (result == 1) {
                        newthis.list();
                        newthis.editkun = false;
                        newthis.$message({
                            message: '修改货柜信息成功',
                            type: 'success'
                        });
                    } else {
                        newthis.$message.error('很遗憾，修改失败');
                    }
                },
                error: function () {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        //保存
        submit() {
            var addForm = this.addForm;
            var newthis = this;
            if (addForm.phone=="" || addForm.phone==null) {
                newthis.$message.error('手机号不能为空');
                return;
            }
            if (addForm.gysfs=="" || addForm.gysfs==null) {
                newthis.$message.error('运输方式不能为空');
                return;
            }
            if (addForm.gnumber=="" || addForm.gnumber==null) {
                newthis.$message.error('货柜号不能为空');
                return;
            }
            if (addForm.gdbfs=="" || addForm.gdbfs==null) {
                newthis.$message.error('打包方式不能为空');
                return;
            }
            if (addForm.bankname=="" || addForm.bankname==null) {
                newthis.$message.error('所属银行不能为空');
                return;
            }
            if (addForm.cgdate=="" || addForm.cgdate==null) {
                newthis.$message.error('出港时间不能为空');
                return;
            }
            if (addForm.banknumber=="" || addForm.banknumber==null) {
                newthis.$message.error('银行卡号不能为空');
                return;
            }
            if (addForm.content=="" || addForm.content==null) {
                newthis.$message.error('货柜内容不能为空');
                return;
            }
            if (addForm.type=="" || addForm.type==null) {
                newthis.$message.error('货柜类型不能为空');
                return;
            }
            if (addForm.count=="" || addForm.count==null) {
                newthis.$message.error('数量（件数）不能为空');
                return;
            }
            if (addForm.price=="" || addForm.price==null) {
                newthis.$message.error('货值不能为空');
                return;
            }
            var d = {
                'phone': addForm.phone,
                'gysfs': addForm.gysfs,
                'gnumber': addForm.gnumber,
                'gdbfs': addForm.gdbfs,
                'gsize': addForm.gsize,
                'bankname': addForm.bankname,
                'cgdate': addForm.cgdate,
                'banknumber': addForm.banknumber,
                'content': addForm.content,
                'type': addForm.type,
                'count': addForm.count,
                'dls': addForm.dls,
                'price': addForm.price
            };
            var url = '/container/add';
            $.ajax({
                type: 'POST',
                url: url,
                data: d,
                dataType: 'json',
                success: function (result) {
                    if (result == 1) {
                        newthis.list();
                        newthis.addkun = false;
                        newthis.$message({
                            message: '恭喜你，添加货柜成功',
                            type: 'success'
                        });
                    } else {
                        newthis.$message.error('很遗憾，添加失败');
                    }
                },
                error: function () {
                    console.log('error submit!!');
                    return false;
                }
            });

        },
        //打开添加页面
        add() {
            let self = this;
            self.addForm = [];
            self.addkun = true;
        },
        //打开出仓单界面
        ccopen(){
            let self = this;
            self.ccdlog = true;

        },
        //打开入仓单界面
        rcopen(){
            let self = this;
            self.rcdlog = true;
        },
        //条件搜索
        seachjxc (){
            var jxcform = this.jxcform;
            var _$this = this;


            var dd1="";
            var dd2="";
            if (jxcform.rcval1==null || jxcform.rcval1=="") {
                dd1="";
                // return;
            }else {
                dd1 = _$this.formatDates(jxcform.rcval1);
            }
            if (jxcform.rcval2==null || jxcform.rcval2=="") {
                dd2="";
            }else{
                //return;
                dd2 = _$this.formatDates(jxcform.rcval2)
            }

            var d = {
                'pnumber': jxcform.pnumber,
                'customerphone': jxcform.customerphone,
                'rcval1': dd1,
                'rcval2': dd2
            };

            var url = '/jxc/seach_ht';
            $.ajax({
                type: 'POST',
                url: url,
                data: d,
                dataType: 'json',
                success: function (result) {
                    if (result.length>0) {
                        _$this.tableData4 = result;
                    } else {
                        _$this.tableData = [];
                    }

                },
                error: function () {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        //条件搜索
        seach (){
            var form = this.form;
            var _$this = this;
            var d = {
                'gnumber': form.gnumber,
                'banknumber': form.banknumber,
                'phone': form.phone
            };
            var url = '/container/seach_ht';
            $.ajax({
                type: 'POST',
                url: url,
                data: d,
                dataType: 'json',
                success: function (result) {
                    if (result.length>0) {
                        _$this.tableData = result;
                    } else {
                        _$this.tableData = [];
                    }

                },
                error: function () {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        //出仓条件查询
        seachcc() {
            var ccform = this.ccform;
            var _$this = this;
            var dd1="";
            var dd2="";
            if (ccform.rcval1==null || ccform.rcval1=="") {

                dd1="";
                // return;
            }else {

                dd1 = _$this.formatDates(ccform.rcval1);
            }
            if (ccform.rcval2==null || ccform.rcval2=="") {
                dd2="";
            }else{
                //return;
                dd2 = _$this.formatDates(ccform.rcval2)
            }

            var d = {
                'orderid': ccform.orderid,
                'customerphone': ccform.customerphone,
                'rcval1': dd1,
                'rcval2': dd2,
            };
            var url = '/cc/seach_ht';
            $.ajax({
                type: 'POST',
                url: url,
                data: d,
                dataType: 'json',
                success: function (result) {
                    if (result.length>0) {
                        _$this.tableData3 = result;
                    } else {
                        _$this.tableData3 = [];
                    }

                },
                error: function () {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        //入仓条件搜索
        seachrc() {
            var rcform = this.rcform;
            var _$this = this;
            var dd1="";
            var dd2="";
            if (rcform.rcval1==null || rcform.rcval1=="") {

                dd1="";
               // return;
            }else {

                dd1 = _$this.formatDates(rcform.rcval1);
            }
            if (rcform.rcval2==null || rcform.rcval2=="") {
                dd2="";
            }else{
                //return;
                dd2 = _$this.formatDates(rcform.rcval2)
            }

            var d = {
                'pnumber': rcform.pnumber,
                'sshg': rcform.sshg,
                'rcval1': dd1,
                'rcval2': dd2,
            };
            var url = '/rc/seach_ht';
            $.ajax({
                type: 'POST',
                url: url,
                data: d,
                dataType: 'json',
                success: function (result) {
                    if (result.length>0) {
                        _$this.tableData2 = result;
                    } else {
                        _$this.tableData2 = [];
                    }

                },
                error: function () {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        jxclist () {
            var newthis = this;
            var url = '/jxc/lists';
            $.ajax({
                type: 'POST',
                url: url,
                dataType: 'json',
                success: function (result) {
                    if (result.length>0) {
                        newthis.tableData4 = result;
                    }
                },
                error: function () {

                }
            });
        },
        list () {
            var newthis = this;
            var url = '/container/lists';
            $.ajax({
                type: 'POST',
                url: url,
                // data: {'id': newthis.input1},
                dataType: 'json',
                success: function (result) {
                    if (result.length>0) {
                        newthis.tableData = result;
                        newthis.loading=false;
                    } else {
                        newthis.loading=false;
                    }
                },
                error: function () {

                }
            });
        },
        //编辑
        handleEdit(index, row) {
            let self = this;
            self.editForm=[];
            self.editkun=true;
            var url = '/container/editselect';
            $.ajax({
                type: 'POST',
                url: url,
                data: {'id': row.id},
                dataType: 'json',
                success: function (result) {
                    self.editForm=result[0]
                },
                error: function () {

                }
            });
        },
        formatDates(d) {
            // 获取单元格数据
            let dt = new Date(d)
            return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate()
        },
        updateformatDate(d) {
            // 获取单元格数据
            let dt = new Date(d)
            return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds();

        },

        //时间格式化
        formatDate(row, column) {
            // 获取单元格数据
            let data = row[column.property]
            if(data == null) {
                return null
            }
            let dt = new Date(data)
            return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds();
        },
        //删除进销存
        handleDeletejxc(index, row) {
            this.$confirm('确定删除该条信息?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                let nthis = this;
                var url = '/jxc/del';
                $.ajax({
                    type: 'POST',
                    url: url,
                    data: {'id': row.id},
                    dataType: 'json',
                    success: function (result) {
                        if (result == 1) {
                            nthis.tableData4.splice(index, 1);
                            nthis.$message({
                                message: '恭喜你，删除成功',
                                type: 'success'
                            });
                        } else {
                            nthis.$message.error('很遗憾，删除失败');
                        }
                    },
                    error: function () {

                    }
                });
            }).catch(() => {
                nthis.$notify.error({
                    title: '取消删除',
                    message: '取消删除！'
                });
            });
            console.log(index, row);
        },
        //删除
        handleDelete(index, row) {
            this.$confirm('确定删除该条报名?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                let nthis = this;
                var url = '/container/del';
                $.ajax({
                    type: 'POST',
                    url: url,
                    data: {'id': row.id},
                    dataType: 'json',
                    success: function (result) {
                        if (result == 1) {
                            nthis.tableData.splice(index, 1);
                            nthis.$message({
                                message: '恭喜你，删除成功',
                                type: 'success'
                            });
                        } else {
                            nthis.$message.error('很遗憾，删除失败');
                        }
                    },
                    error: function () {

                    }
                });
            }).catch(() => {
                nthis.$notify.error({
                    title: '取消删除',
                    message: '取消删除！'
                });
            });
            console.log(index, row);
        },
        //出仓删除
        handleDeletecc(index, row) {
            this.$confirm('确定删除该条报名?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                let nthis = this;
                var url = '/cc/del';
                $.ajax({
                    type: 'POST',
                    url: url,
                    data: {'id': row.id},
                    dataType: 'json',
                    success: function (result) {
                        if (result == 1) {
                            nthis.tableData3.splice(index, 1);
                            nthis.$message({
                                message: '恭喜你，删除成功',
                                type: 'success'
                            });
                        } else {
                            nthis.$message.error('很遗憾，删除失败');
                        }
                    },
                    error: function () {

                    }
                });
            }).catch(() => {
                nthis.$notify.error({
                    title: '取消删除',
                    message: '取消删除！'
                });
            });
            console.log(index, row);
        },
        //入仓删除
        handleDeleterc(index, row) {
            this.$confirm('确定删除该条报名?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                let nthis = this;
                var url = '/rc/del';
                $.ajax({
                    type: 'POST',
                    url: url,
                    data: {'id': row.id},
                    dataType: 'json',
                    success: function (result) {
                        if (result == 1) {
                            nthis.tableData2.splice(index, 1);
                            nthis.$message({
                                message: '恭喜你，删除成功',
                                type: 'success'
                            });
                        } else {
                            nthis.$message.error('很遗憾，删除失败');
                        }
                    },
                    error: function () {

                    }
                });
            }).catch(() => {
                nthis.$notify.error({
                    title: '取消删除',
                    message: '取消删除！'
                });
            });
            console.log(index, row);
        },

        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        handleSelectionChangegl(val) {
            this.multipleSelection = val;
        },
        handleSelectionChangejxc(val) {
            this.multipleSelection = val;
        },

        //table标签触发事件
        tableclick (tab, event){
            var newthis = this;
            if(tab.index==0) {
                newthis.list();
            }
            if(tab.index==1) {
                newthis.listrc();
                setTimeout(function (){
                    newthis.getSummariesrc();
                },1500);
            }
            if(tab.index==2) {
                newthis.listcc();
                setTimeout(function (){
                    newthis.getSummariescc();
                },1500);
            }
            if(tab.index==3) {
                newthis.jxclist();
                setTimeout(function (){
                    newthis.getSummariesjxc();
                },1500);
            }
        },
        //list search
        listcc () {
            var newthis = this;
            var url = '/cc/lists';
            $.ajax({
                type: 'POST',
                url: url,
                // data: {'id': newthis.input1},
                dataType: 'json',
                success: function (result) {
                    if (result.length>0) {
                        newthis.tableData3 = result;
                        newthis.loading=false;
                    } else {
                        newthis.loading=false;
                    }
                },
                error: function () {

                }
            });
        },
        //list search
        listrc () {
            var newthis = this;
            var url = '/rc/lists';
            $.ajax({
                type: 'POST',
                url: url,
                // data: {'id': newthis.input1},
                dataType: 'json',
                success: function (result) {
                    if (result.length>0) {
                        newthis.tableData2 = result;
                        newthis.loading=false;
                    } else {
                        newthis.loading=false;
                    }
                },
                error: function () {

                }
            });
        },
        // 初始页currentPage、初始每页数据数pagesize和数据data
        handleSizeChange: function (size) {
            this.pagesize = size;
            console.log(this.pagesize)  //每页下拉显示数据
        },
        handleCurrentChange: function (currentPage) {
            this.currentPage = currentPage;
            console.log(this.currentPage)  //点击第几页
        },
        // 初始页currentPage、初始每页数据数pagesize和数据data
        handleSizeChangegl: function (size) {
            this.pagesizegl = size;
            console.log(this.pagesizegl)  //每页下拉显示数据
        },
        handleCurrentChangegl: function (currentPagegl) {
            this.currentPagegl = currentPagegl;
            console.log(this.currentPagegl)  //点击第几页
        },
        // 初始页currentPage、初始每页数据数pagesize和数据data
        handleSizeChangerc: function (size) {
            this.pagesizerc = size;
            console.log(this.pagesizerc)  //每页下拉显示数据
        },
        handleCurrentChangerc: function (currentPagerc) {
            this.currentPagerc = currentPagerc;
            console.log(this.currentPagerc)  //点击第几页
        },
        // 初始页currentPage、初始每页数据数pagesize和数据data
        handleSizeChangejxc: function (size) {
            this.pagesizejxc = size;
            console.log(this.pagesizejxc)  //每页下拉显示数据
        },
        handleCurrentChangejxc: function (currentPagejxc) {
            this.currentPagejxc = currentPagejxc;
            console.log(this.currentPagejxc)  //点击第几页
        },
        handleSizeChangecc: function (size) {
            this.pagesizecc = size;
            console.log(this.pagesizecc)  //每页下拉显示数据
        },
        handleCurrentChangecc: function (currentPagerc) {
            this.currentPagecc = currentPagecc;
            console.log(this.currentPagecc)  //点击第几页
        },
        handleSelectionChangerc(val) {
            this.multipleSelection = val;
        },
        handleSelectionChangecc(val) {
            this.multipleSelection = val;
        },
        //出仓动态添加物品信息
        addOrHanldercc(index) {
            let obj = {
                pnumber: '',
                pname: '',
                color: '',
                ccprice: '',
                cccount: '',
                ccsumprice: '',
                type: '',
                indexid: index+1
            };
            this.ccdynamicValidateForm.rcdetil.push(obj);
        },
        //入仓动态添加物品信息
        addOrHanlderrc(index) {
            let obj = {
                pnumber: '',
                pname: '',
                color: '',
                costprice: '',
                costcount: '',
                rccount: '',
                type: '',
                rcdate: '',
                indexid: index+1
            };
            this.rcdynamicValidateForm.rcdetil.push(obj);
        },
        //入仓动态添加物品信息删除行
        deleteOrHandlerrc(every, index) {
            this.rcdynamicValidateForm.rcdetil.splice(index, 1);
        },
        //出仓动态添加物品信息删除行
        deleteOrHandlercc(every, index) {
            this.ccdynamicValidateForm.rcdetil.splice(index, 1);
        },
        //入库取消
        fh () {
            this.rcdlog=false;
            this.rcdynamicValidateForm={
                rcdetil: [{
                    pnumber: '',
                    pname: '',
                    color: '',
                    costprice: '',
                    costcount: '',
                    rccount: '',
                    type: '',
                    bz: '',
                    gys: '',
                    sshg: '',
                    rcdate: ''
                }]
            }
        },
        //出库取消
        ccfh(){
            this.ccdlog=false;
            this.ccorder = Date.now();
        },
        //保存出库单
        orSaveHandlercc(){
            var flag=0;
            var k =[];
            var from = this.ccdynamicValidateForm;
            var list = JSON.stringify(from.rcdetil);

            var newthis = this;
            if (from.customername=="" || from.customername==null){
                newthis.$message.error('客户姓名为空');
                return;
            }
            if (from.customeraddress =="" || from.customeraddress==null){
                newthis.$message.error('运输地址为空');
                return;
            }
            if (from.ccdate =="" || from.ccdate==null){
                newthis.$message.error('出库时间为空');
                return;
            }

            if (from.customerphone =="" || from.customerphone==null){
                newthis.$message.error('客户联系电话为空');
                return;
            }
            for(var h=0;h<from.rcdetil.length;h++){
                    if (from.rcdetil[h].pnumber=="" || from.rcdetil[h].pnumber==null){
                        newthis.$message.error('商品'+(h+1)+'编码为空');
                        return;
                    }
                    if (from.rcdetil[h].pname=="" || from.rcdetil[h].pname==null){
                        newthis.$message.error('商品'+(h+1)+'名称为空');
                        return;
                    }

                    if (from.rcdetil[h].cccount=="" || from.rcdetil[h].cccount==null){
                        newthis.$message.error('商品'+(h+1)+'出仓数量为空');
                        return;
                    }

                    if (from.rcdetil[h].color=="" || from.rcdetil[h].color==null){
                        newthis.$message.error('商品'+(h+1)+'颜色为空');
                        return;
                    }
                    if (from.rcdetil[h].type=="" || from.rcdetil[h].type==null){
                        newthis.$message.error('商品'+(h+1)+'规格为空');
                        return;
                    }
                    if (from.rcdetil[h].ccprice=="" || from.rcdetil[h].ccprice==null){
                        newthis.$message.error('商品'+(h+1)+'单价为空');
                        return;
                    }
                //根据商品 pnumber 查询该商品的的库存数
                //请求后台
                var urls = '/rc/selectrccount';
                $.ajax({
                    type: 'POST',
                    url: urls,
                    async:false,
                    data: {'pnumber': from.rcdetil[h].pnumber,'cccount':from.rcdetil[h].cccount},
                    dataType: 'json',
                    success: function (res) {
                        //比较输入的是否超出 如果超出则进行提示 不予通过
                        if (res==1) {
                            newthis.flag=1;
                            return false;
                        }
                        if(res==3) {
                            newthis.flag=2;
                            return false;
                        }
                    },
                    error: function () {
                        console.log('error submit!!');
                        return false;
                    }
                });

            }
            if(newthis.flag==1){
                newthis.$message.error('商品存在库存不足！！！');
                newthis.flag="0";
                return;
            }
            if(newthis.flag==3){
                newthis.$message.error('目前仓库中无该商品，无法下单');
                newthis.flag="0";
                return;
            }
            var d = {
                'list': list,
                'ccorder':newthis.ccorder,
                'customerphone':from.customerphone,
                'customername':from.customername,
                'customeraddress':from.customeraddress,
                'ccdate':JSON.stringify(from.ccdate)
            };
            //请求后台
            var url = '/cc/add';
            $.ajax({
                type: 'POST',
                url: url,
                data: d,
                dataType: 'json',
                success: function (result) {
                    if (result == 1) {
                        newthis.ccdlog=false;
                        newthis.$message({
                            message: '保存成功',
                            type: 'success'
                        });
                        newthis.listcc();
                    } else {
                        newthis.$message.error('设置失败');
                    }
                },
                error: function () {
                    console.log('error submit!!');
                    return false;
                }
            });
            console.log('submit!');
        },
        //保存入库单
        orSaveHandlerrc(){
            var from = this.rcdynamicValidateForm;
            var list = JSON.stringify(from.rcdetil);

            var newthis = this;
            for(var h=0;h<from.rcdetil.length;h++){
                if (from.rcdetil[h].qcs=="" || from.rcdetil[h].qcs==null){
                    from.rcdetil[h].qcs ="0";
                }
                if (from.rcdetil[h].pnumber=="" || from.rcdetil[h].pnumber==null){
                    newthis.$message.error('商品'+(h+1)+'编码为空');
                    return;
                }
                if (from.rcdetil[h].pname=="" || from.rcdetil[h].pname==null){
                    newthis.$message.error('商品'+(h+1)+'名称为空');
                    return;
                }
                if (from.rcdetil[h].rcdate=="" || from.rcdetil[h].rcdate==null){
                    newthis.$message.error('商品'+(h+1)+'入仓时间为空');
                    return;
                }
                if (from.rcdetil[h].pnumber=="" || from.rcdetil[h].pnumber==null){
                    newthis.$message.error('商品'+(h+1)+'编码为空');
                    return;
                }
                if (from.rcdetil[h].rccount=="" || from.rcdetil[h].rccount==null){
                    newthis.$message.error('商品'+(h+1)+'入仓适量为空');
                    return;
                }
                if (from.rcdetil[h].color=="" || from.rcdetil[h].color==null){
                    newthis.$message.error('商品'+(h+1)+'颜色为空');
                    return;
                }
                if (from.rcdetil[h].gys=="" || from.rcdetil[h].gys==null){
                    newthis.$message.error('商品'+(h+1)+'供应商信息为空');
                    return;
                }
                if (from.rcdetil[h].type=="" || from.rcdetil[h].type==null){
                    newthis.$message.error('商品'+(h+1)+'规格为空');
                    return;
                }
                if (from.rcdetil[h].costprice=="" || from.rcdetil[h].costprice==null){
                    newthis.$message.error('商品'+(h+1)+'成本单价为空');
                    return;
                }
                if (from.rcdetil[h].sshg=="" || from.rcdetil[h].sshg==null){
                    newthis.$message.error('商品'+(h+1)+'所属货柜编码为空');
                    return;
                }
            }
            var d = {
                'list': list,
            };
            //请求后台
            var url = '/rc/add';
            $.ajax({
                type: 'POST',
                url: url,
                data: d,
                dataType: 'json',
                success: function (result) {
                    if (result == 1) {
                        newthis.rcdlog=false;
                        newthis.$message({
                            message: '保存成功',
                            type: 'success'
                        });
                        newthis.listrc();
                    } else {
                        newthis.$message.error('设置失败');
                    }
                },
                error: function () {
                    console.log('error submit!!');
                    return false;
                }
            });
            console.log('submit!');
        },
        rcs(index){
            var newthis =this;
           var s1 = parseInt(this.rcdynamicValidateForm.rcdetil[index].rccount);
           var s2 = parseInt(this.rcdynamicValidateForm.rcdetil[index].costprice);
            if (Number.isNaN(s1)){
                newthis.$message.error('入库数为空，请填写入库数');
                return;
            }
            if (Number.isNaN(s2)){
                newthis.$message.error('成本单价为空，请填写入成本单价');
                return;
            }
            document.getElementById(index).value = s1 * s2;
        },
        cbdj(index){
            var newthis =this;
            var s1 = parseInt(this.rcdynamicValidateForm.rcdetil[index].rccount);
            var s2 = parseInt(this.rcdynamicValidateForm.rcdetil[index].costprice);
            if (Number.isNaN(s1)){
                newthis.$message.error('入库数为空，请填写入库数');
                return;
            }
            if (Number.isNaN(s2)){
                newthis.$message.error('成本单价为空，请填写入成本单价');
                return;
            }
            document.getElementById(index).value = s1 * s2;
        },
        //出库计算
        rescc(index){
            var newthis =this;
            var s1 = parseInt(this.ccdynamicValidateForm.rcdetil[index].cccount);
            var s2 = parseInt(this.ccdynamicValidateForm.rcdetil[index].ccprice);
            if (Number.isNaN(s1)){
                newthis.$message.error('出库数为空，请填写出库数');
                return;
            }
            if (Number.isNaN(s2)){
                newthis.$message.error('单价为空，请填输入单价');
                return;
            }
            document.getElementById(index).value = s1 * s2;
        },
        cbdjcc(index){
            var newthis =this;
            var s1 = parseInt(this.ccdynamicValidateForm.rcdetil[index].cccount);
            var s2 = parseInt(this.ccdynamicValidateForm.rcdetil[index].ccprice);
            if (Number.isNaN(s1)){
                newthis.$message.error('出库数为空，请填写出库数');
                return;
            }
            if (Number.isNaN(s2)){
                newthis.$message.error('单价为空，请填输入单价');
                return;
            }
            document.getElementById(index).value = s1 * s2;
        },

        //入库
        getSummariesrc(param) {
            const { columns, data } = param;
            const sums = [];
            columns.forEach((column, index) => {
                if (index === 0) {
                    sums[index] = '合 计';
                    return;
                }

                if (index === 1) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 2) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 3) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 4) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 5) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 9) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 10) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 11) {
                    sums[index] = ' ';
                    return;
                }


                const values = data.map(item => Number(item[column.property]));
                if (!values.every(value => isNaN(value))) {
                    sums[index] = values.reduce((prev, curr) => {
                        const value = Number(curr);
                        if (!isNaN(value)) {
                            return prev + curr;
                        } else {
                            return prev;
                        }
                    }, 0);
                    if(index ===8) {
                        sums[index] += ' 件';
                    }else if(index ===6) {
                        sums[index] += ' 件';
                    } else if(index ===7) {
                        sums[index] += ' 件';
                    } else {
                        sums[index] += ' ';
                    }


                } else {
                    sums[index] = 'N/A';
                }
            });

            return sums;
        },
        //进销存合计
        getSummariesjxc(param) {
            const { columns, data } = param;
            const sums = [];
            columns.forEach((column, index) => {
                if (index === 0) {
                    sums[index] = '合 计';
                    return;
                }

                if (index === 1) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 2) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 3) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 4) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 11) {
                    sums[index] = ' ';
                    return;
                }


                const values = data.map(item => Number(item[column.property]));
                if (!values.every(value => isNaN(value))) {
                    sums[index] = values.reduce((prev, curr) => {
                        const value = Number(curr);
                        if (!isNaN(value)) {
                            return prev + curr;
                        } else {
                            return prev;
                        }
                    }, 0);
                    if(index ===8) {
                        sums[index] += ' 件';
                    }else if(index ===9) {
                        sums[index] += ' 件';
                    } else if(index ===10) {
                        sums[index] += ' 件';
                    } else if(index ===5) {
                        sums[index] += ' 件';
                    } else if(index ===6) {
                        sums[index] += ' 件';
                    } else if(index ===7) {
                        sums[index] += ' 件';
                    } else {
                        sums[index] += ' ';
                    }


                } else {
                    sums[index] = 'N/A';
                }
            });

            return sums;
        },
        getSummariescc(param) {
            const { columns, data } = param;
            const sums = [];
            columns.forEach((column, index) => {
                if (index === 0) {
                    sums[index] = '合 计';
                    return;
                }
                if (index === 1) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 2) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 3) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 4) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 5) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 6) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 10) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 11) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 12) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 13) {
                    sums[index] = ' ';
                    return;
                }
                if (index === 14) {
                    sums[index] = ' ';
                    return;
                }
                const values = data.map(item => Number(item[column.property]));
                if (!values.every(value => isNaN(value))) {
                    sums[index] = values.reduce((prev, curr) => {
                        const value = Number(curr);
                        if (!isNaN(value)) {
                            return prev + curr;
                        } else {
                            return prev;
                        }
                    }, 0);
                    if(index ===8) {
                        sums[index] += ' 元';
                    }else if(index ===9) {
                        sums[index] += ' 元';
                    } else {
                        sums[index] += ' ';
                    }


                } else {
                    sums[index] = 'N/A';
                }
            });

            return sums;
        },
        //点击行事件
        rowclick(row, column, event) {
            var newthis = this;
            var d = {
                'gnumber': row.gnumber,
            };
            //请求后台
            var url = '/rc/gl';
            $.ajax({
                type: 'POST',
                url: url,
                data: d,
                dataType: 'json',
                success: function (result) {
                    newthis.tableData5 = result;
                },
                error: function () {
                    console.log('error submit!!');
                    return false;
                }
            });

        }
    }
})