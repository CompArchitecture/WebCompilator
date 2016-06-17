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
                    } else {
                        showInvalidFileInfo();
                    }
                },
                cache: false,
                contentType: false,
                processData: false
            });
        });
    });
})();

function showInProgressInfo(){
    hideShowInfo();
    $("#uploadForm").css("pointer-events", "none").css("opacity", "0.5");
    $("#testInProcess").css("display", "block");
}

function hideInProgressInfo(){
    $("#uploadForm").css("pointer-events", "").css("opacity", "1");
    $("#testInProcess").css("display", "none");
}

function showInvalidFileInfo(){
    hideInProgressInfo();
    $("#invalidFile").css("display", "block");
}

function hideShowInfo(){
    $("#invalidFile").css("display", "none");
}