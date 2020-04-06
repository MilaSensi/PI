$(document).ready(function () {
    $('.js-button-reserve').click(function () {
        var service = $('#service option:selected').val();
        var spec = $('#spec option:selected').val();
        var date = $('#date').val();
        if (!service || !spec || !date) {
            alert('Заполните поля для резервирования');
            return;
        }
        var reserve = {service: parseInt(service), spec: parseInt(spec), date: date};
        $.ajax
        ({
            dataType: "json",
            type: "POST",
            url: '/client/reserve',
            data: reserve,
            success: function (data) {
                $('#table-my-orders tbody')[0].innerHTML = '';
                var rows = '';
                for (var orderId in data) {
                    rows += '<tr>';
                    rows += '<td style="width: 60px">' + data[orderId].id + '</td>';
                    rows += '<td>' + data[orderId].photoService.name + '</td>';
                    rows += '<td>' + data[orderId].photoService.price + '</td>';
                    rows += '<td>' + data[orderId].dateStart + '</td>';
                    rows += '<td>' + data[orderId].specialist + '</td>';
                    rows += '<td>' + data[orderId].status + '</td>';
                    rows += '</tr>';
                }
                $('#table-my-orders tbody')[0].innerHTML = rows;
                alert("Заказ успешно оформлен");
            }
        })
    });
});
