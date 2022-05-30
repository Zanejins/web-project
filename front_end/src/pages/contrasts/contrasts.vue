<template>
  <div>
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <el-upload
      multiple
      drag
      action="https://jsonplaceholder.typicode.com/posts/"
      :file-list="fileArr"
      :before-upload="beforeUpload"
      :on-change="imgPreview"
      :on-preview="handlefileList"
      :on-remove="handleRemove"
      :on-success="successUpload"
      :on-error="errorUpload"
      :limit="3"
    >
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <div class="el-upload__tip" slot="tip">
        只能上传.pdf文件，且不超过2MB
        <el-button class="download" @click="downloadFile(fileList)" type="text">下载</el-button>
      </div>
    </el-upload>
    <div style="width:75%;margin:auto">
      <!-- 遍历总页数 渲染每一页-->
      <pdf v-for="page in numPages" :key="page" :src="pdfUrl" :page="page"> </pdf>
    </div>
  </div>
</template>

<script>
import pdf from "vue-pdf";  
export default {
  components: { pdf },
  data() {
    return {
      file:null,
      numPages: "",
      fileArr: [],
      pdfUrl: "" // 展示的pdf文件地址
    };
  },
  methods: {
    errorUpload() {
      this.$message({
        message: "上传成功",
        type: "success"
      });
    },
    successUpload() {
      this.$message({
        message: "上传成功",
        type: "success"
      });
    },
    beforeUpload(file) {
      var is2M = file.size / 1024 / 1024 < 2;
      if (!is2M) {
        this.$message({
          message: "上传文件大小不能超过 2MB!",
          type: "warning"
        });
        return false;
      }
      //let regex = /(.jpg|.jpeg|.gif|.png|.pdf)$/;
      let regex = /(.pdf)$/;
      if (regex.test(file.name.toLowerCase())) {
        console.log("校验通过");
      } else {
        this.$message.warning("只能上传.pdf文件");
        return false;
      }
    },
    handlefileList(file) {
      // 点击已上传文件列表的钩子
      this.pdfUrl = this.getPdfUrl(file);
      this.file=file
      console.log(this.pdfUrl)
    },
    handleRemove(file) {
      this.pdfUrl = "";
    },
    imgPreview(file) {},
    //处理文件
    getPdfUrl(file) {
      console.log(file.raw)
      let url = URL.createObjectURL(file.raw); //将文件转化成url
      let loadingTask = pdf.createLoadingTask(url);
      loadingTask.promise.then(pdf => {
        this.numPages = pdf.numPages;
        console.log(this.numPages);
      });
      return url;
    },
    downloadFile(){
      const link = document.createElement("a");
      link.style.display="none";
      link.href=this.pdfUrl;
      link.setAttribute('download',"模板.pdf");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    },
  }
};
</script>

