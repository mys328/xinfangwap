var intervalCounter = 0;

function hideToast() {
    var alert = document.getElementById("toast");
    alert.style.opacity = 0;
    clearInterval(intervalCounter);
}
function drawToast(message) {
    var alert = document.getElementById("toast");
    if (alert == null) {
        var toastHTML = "<div id='toast'>" + message + "</div>";
        document.body.insertAdjacentHTML("beforeEnd", toastHTML);
    } else {
        alert.style.opacity = .9;
    }
    intervalCounter = setInterval("hideToast()", 1000);
}
function save(m) {
    drawToast(m);
}
