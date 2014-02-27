var types = "doc,xls,mp3,wmv,rar,txt,jpg,gif,zip";
types = types.split(",");
function upload() {

    var filevalue = document.getElementById("image").value;

    if (filevalue == "") {
        alert("请选择文件");
        return false;
    }

    //文件类型
    var suffix = filevalue.substring(filevalue.lastIndexOf(".") + 1);
    suffix = suffix.toLowerCase();

    //附件类型验证
    var canUpload = false;
    for (var i = 0; i < types.length; i++) {
        var fj_type = types[i].toLowerCase();
        if (fj_type == suffix) canUpload = true;
    }
    if (!canUpload) {
        alert("只能上传doc,xls,mp3,wmv,rar,txt,jpg,gif,zip等类型的附件");
        return false;
    }
    checkFileSize();

    var fileXmlvalue = document.getElementById("fileXml").value;

    var index;
    if (fileXmlvalue == "" || fileXmlvalue == "<Files Domain='jscx' Path=''></Files>" || fileXmlvalue == "<Files Domain='jscx' Path=''/>") {
        index = "1";
    } else {
        var xmlDoc = StringToXml(fileXmlvalue);
        var files = xmlDoc.getElementsByTagName("Files");
        var fileLenght = files[0].childNodes.length;
        index = fileLenght + 1 + "";
    }
    //给form添加action属性
    var action = "UserInfo_upload?businessNUM=0006&method=webUpload&sesionId=1234&suffix=" + suffix + "&index=" + index;

    var filelist = document.getElementById("filelist");
    var xml = document.getElementById("xml");

    $('#uploadForm').ajaxForm({
        url:action,
        type:'post',
        dataType:'xml',
        async:false,
        statusCode:{
            404:function () {
                alert("page not found");
            },
            500:function () {
                alert("page not found");
            },
            400:function () {
                alert("page not found");
            }
        },
        success:function (data) {

            var filetitle = $(data).find("File").attr("Title");
            var index = $(data).find("File").attr("Index");
            filelist.innerHTML += "<span id='" + index + "'>[新增]"
                + filetitle + "&nbsp;&nbsp;&nbsp;<a onclick='deleteFile(" + index + ")'>删除</a></span>"
                + "<br id='" + index + "br'/>";
            xml.style.display = "";
            var fileXml = document.getElementById("fileXml");
            if (fileXml.value == "") {
                fileXml.value = "<Files Domain='jscx' Path=''>"
                    + "</Files>";
            }
            //获取的xml
            var file = data.getElementsByTagName("File")[0];
            //input的值
            var xmlDoc = StringToXml(fileXml.value);
            var files = xmlDoc.getElementsByTagName("Files");
            files[0].appendChild(file);
            fileXml.value = xmlToString(xmlDoc);

        },
        error: function(data){
            alert(data);
        }
    });

}
//将xml对象转为String
function xmlToString(xmlData) {
    var xmlString;
    //IE
    if (window.ActiveXObject) {
        xmlString = xmlData.xml;
    }
    // code for Mozilla, Firefox, Opera, etc.
    else {
        xmlString = (new XMLSerializer()).serializeToString(xmlData);
    }
    return xmlString;
}
function StringToXml(text) {
    var xmlDoc;
    try //Internet Explorer
    {
        xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
        xmlDoc.async = "false";
        xmlDoc.loadXML(text);
    }
    catch (e) {
        try {
            parser = new DOMParser();
            xmlDoc = parser.parseFromString(text, "text/xml");
        } catch (e) {
            alert(e.message);
        }
    }
    return xmlDoc;
}

//检查上传文件大小
function checkFileSize() {
    var maxsize = 2 * 1024 * 1024;//2M
    var errMsg = "上传的附件文件不能超过2M！！！";
    var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过2M，建议使用IE、FireFox、Chrome浏览器。";
    var browserCfg = {};
    var ua = window.navigator.userAgent;
    if (ua.indexOf("MSIE") >= 1) {
        browserCfg.ie = true;
    } else if (ua.indexOf("Firefox") >= 1) {
        browserCfg.firefox = true;
    } else if (ua.indexOf("Chrome") >= 1) {
        browserCfg.chrome = true;
    }
    function checkfile() {
        try {
            var obj_file = document.getElementById("image");
            var filesize = 0;
            if (browserCfg.firefox || browserCfg.chrome) {
                filesize = obj_file.files[0].size;
            } else if (browserCfg.ie) {
                var obj_img = document.getElementById('tempimg');
                obj_img.dynsrc = obj_file.value;
                filesize = obj_img.fileSize;
            } else {
                alert(tipMsg);
                return false;
            }
            if (filesize == -1) {
                alert(tipMsg);
                return false;
            } else if (filesize > maxsize) {
                alert(errMsg);
                return false;
            }
        } catch (e) {
            alert(e);
        }
    }
}

function deleteFile(index) {
    //删除文件
    var span = document.getElementById(index);
    var br = document.getElementById(index + "br");
    var filelist = document.getElementById("filelist");
    filelist.removeChild(span);
    filelist.removeChild(br);
    //修改上传的xml
    var fileXml = document.getElementById("fileXml");
    var xmlDoc = StringToXml(fileXml.value);
    var files = xmlDoc.getElementsByTagName("Files");
    var file = files[0].childNodes;
    for (var i = 0; i < file.length; i++) {
        if (file[i].attributes.getNamedItem("Index").value + "" == index) {
            files[0].removeChild(file[i]);
        }
    }
    fileXml.value = xmlToString(xmlDoc);
}