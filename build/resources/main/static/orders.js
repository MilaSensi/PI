$(document).ready(function () {
    $('select').on('change', function () {
        $.ajax
        ({
            type: "POST",
            url: '/orders/status',
            data: {paymentId: this.id, statusId: this.options[this.options.selectedIndex].value},
            success: function (data) {
                alert("Статус успешно изменен");
            },
            error: function (error) {
                console.log(error)
            }
        })
    });
});