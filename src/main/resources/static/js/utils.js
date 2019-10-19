function ajaxForm(myForm, successCallback) {
    myForm.submit(function (e) {
        $.ajax({
            type: myForm.attr('method'),
            url: myForm.attr('action'),
            data: myForm.serialize(), // serializes the form's elements.
            success: function (data) {
                myForm.trigger("reset");
                successCallback && successCallback(data);
            },
            error: function (data) {
                toastr.error(data.responseJSON.errors.join(', '));
            }
        });
        e.preventDefault(); // avoid to execute the actual submit of the form.
    });
}

function ajaxDelete(el, alertMsg, url, successCallback) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    if (token && header) {
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    }

    var confirmed = confirm(alertMsg);
    if (confirmed) {
        $.ajax({
            url: url,
            type: 'DELETE',
            success: function (result) {
                successCallback && successCallback(result);
            }
        });
    }
}