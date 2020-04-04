$(document).ready(function() {
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
                console.log(data);
                alert(data);
            }
        })
    });
});
