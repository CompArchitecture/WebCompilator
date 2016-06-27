(function () {
    $(document).ready(function () {
        $("#uploadForm").submit(function (event) {
            //disable the default form submission
            event.preventDefault();
            var formData = new FormData($(this)[0]);

            $.ajax({
                url: "UploadServlet",
                type: 'POST',
                data: formData,
                success: function (data) {
                    if (data == "uploaded") {
                        $("#testInProcess").css("display", "block");
                        showInProgressInfo();
                        startInterval();
                    } else {
                        showInvalidFileInfo();
                    }
                },
                cache: false,
                contentType: false,
                processData: false
            });
        });
        $("#resetBtn").click(function () {
            $("#resetBtn").css("display", "none");
                $("#uploadFileText").css("display", "block");
                resetAll()
            }
        );
    });
})();

function showInProgressInfo() {
    hideShowInfo();
    $("#uploadForm").css("pointer-events", "none").css("opacity", "0.5");
    $("#testInProcess").css("display", "block");
}

function hideInProgressInfo() {
    hideShowInfo();
    $("#testInProcess").css("display", "none");
}

function enableUpload() {
    $("#uploadForm").css("pointer-events", "").css("opacity", "1");
}

function showInvalidFileInfo() {
    hideInProgressInfo();
    $("#invalidFile").css("display", "block");
}

var timer = null;

function hideShowInfo() {
    $("#invalidFile").css("display", "none");
}

function showShowInfo() {
    $("#invalidFile").css("display", "");
}

function checkInfo() {
    var formData = new FormData($(this)[0]);
    $.ajax({
        url: "ResultServlet",
        type: 'POST',
        data: formData,
        success: function (data) {
            if (data != "wait") {
                hide("#uploadFileText");
                hideInProgressInfo();
                stopInterval();
                showResult(data);
            }
        },
        cache: false,
        contentType: false,
        processData: false
    });
}

function showResult(message) {
    $("#resultText").text(message);
    $("#testResult").css("display", "block");
    $("#resetBtn").css("display", "block");
}

function startInterval() {
    timer = setInterval("checkInfo()", 10);
}

function stopInterval() {
    clearInterval(timer);
}

function resetAll() {
    enableUpload();
    hide("#testInProcess");
    hide("#testResult");
    hide("#resetBtn");
    var control = $("#uploadForm");
    control.replaceWith(control.clone( true ) );
}


function hide(text) {
    $(text).css("display", "none");
}