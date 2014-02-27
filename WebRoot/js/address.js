function decodeString(strData) {

    strData = strData.replace(new RegExp("&lt;", "gm"), "<");
    strData = strData.replace(new RegExp("&gt;", "gm"), ">");
    strData = strData.replace(new RegExp("&apos;", "gm"), "&apos;");
    strData = strData.replace(new RegExp("&quot;", "gm"), "\"");
    strData = strData.replace(new RegExp("&amp;", "gm"), "&");

    return strData;
}

function sendSelect(id, classes, islogin) {

    //创建XMLHttpRequest对象
    var xmlhttp;
    var parser;
    var isIE;
    var changeNode;
    var classkey;
    var classvalue;
    //配置XMLHttpRequest对象
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
        parser = new DOMParser();
        isIE = false;
    } else {
        xmlhttp = new ActiveXObject("microsoft.XMLHTTP");
        parser = new ActiveXObject("Microsoft.XMLDOM");
        parser.async = "false";
        isIE = true;

    }
    if (classes == 2) {
        changeNode = document.getElementById("city");
    }
    if (classes == 3) {
        changeNode = document.getElementById("county");
    }
    classkey = document.getElementById(classes + "classes_key");
    classvalue = document.getElementById(classes + "classes_value");

    //标签
    if (islogin == "islogin") {
        xmlhttp.open("get", "UserInfo_ajax?businessNUM=0004&id=" + id, true);
    } else {
        xmlhttp.open("get", "UserInfo_noLoginAjax?businessNUM=0004&id=" + id, true);
    }

    //发送请求
    xmlhttp.send(null);
    xmlhttp.onreadystatechange = function show() {
        if (xmlhttp.readyState == 4) {
            if (xmlhttp.status == 200) {
                var doc = xmlhttp.responseText;
                var ss = decodeString(doc);

                var pro;
                if (isIE) {
                    parser.loadXML(ss);
                    pro = parser.getElementsByTagName("provs");
                } else {
                    xmlDoc = parser.parseFromString(ss, "text/xml");
                    pro = xmlDoc.getElementsByTagName("provs");

                }

                if (pro.length != 0) {
                    classkey.style.display = "";
                    classvalue.style.display = "";
                } else {
                    classkey.style.display = "none";
                    classvalue.style.display = "none";
                }
                if (pro.length > 1) {
                    changeNode.options.length = 0;

                }

                for (var i = 0; i < pro.length; i++) {
                    var p = pro[i];
                    changeNode.add(new Option(p.childNodes[0].firstChild.data, p.childNodes[1].firstChild.data));

                }

            }
        }
    }

}

function processProv(classes) {
    var classkey;
    var classvalue;
    if (classes == 1) {

        classkey = document.getElementById("1classes_key");
        classvalue = document.getElementById("1classes_value");

        classkey1 = document.getElementById("4classes_key");
        classvalue1 = document.getElementById("4classes_value");

    }

    classkey.style.display = "";
    classvalue.style.display = "";

    classkey1.style.display = "";
    classvalue1.style.display = "";
    document.getElementById("province").selectedIndex=0;
}
function commit_address(sfd) {

    var jtzz;
    for (var classes = 1; classes < 4; classes++) {
        var dd = classes.toString() + "classes_value";
        var classValue = document.getElementById(dd);

        if (classValue.style.display != "none") {
            var obj;
            if (classes == 1) {
                obj = document.getElementById("province");
            }
            if (classes == 2) {
                obj = document.getElementById("city");
            }
            if (classes == 3) {
                obj = document.getElementById("county");
            }
            var index = obj.selectedIndex; // 选中索引
            var text = obj.options[index].text.replace(/(^\s*)|(\s*$)/g, ""); // 选中文本
            jtzz = jtzz + text;
        }
    }

    if (sfd == "jtzz") {
        var detailaddress = document.getElementById("detailaddress").value;
        jtzz = jtzz + detailaddress.replace(/(^\s*)|(\s*$)/g, "");
    }
    jtzz = jtzz.replace("undefined", "");

    document.getElementById(sfd).value = "";
    document.getElementById(sfd).value = jtzz;

    //隐藏
    for (var j = 1; j < 5; j++) {

        var key = j.toString() + "classes_key";
        var value = j.toString() + "classes_value";
        var keyDiv = document.getElementById(key);
        var valueDiv = document.getElementById(value);
        keyDiv.style.display = "none";
        valueDiv.style.display = "none";
    }


}