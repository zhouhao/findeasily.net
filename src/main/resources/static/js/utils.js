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