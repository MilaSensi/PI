$(document).ready(function () {
    function getMessages() {
        var personId = $('.message-person.selected').attr('data');
        var currentPersonId = $('#currentPersonId').attr('data');
        var currentPersonType = $('#currentPersonType').attr('data');

        if (currentPersonType === 'CLIENT') {
            var client = currentPersonId;
            var admin = personId;
        } else {
            var client = personId;
            var admin = currentPersonId;
        }
        if (!personId || !currentPersonId || !currentPersonType) {
            alert('Заполните поля для резервирования');
            return;
        }
        var params = {client: parseInt(client), admin: parseInt(admin)};
        $.ajax
        ({
            dataType: "json",
            type: "GET",
            url: '/messages/get',
            data: params,
            success: function (data) {
                $('.chat-body')[0].innerHTML = '';
                var messages = '';
                for (var messageId in data) {
                    if (data[messageId].sender === 'CLIENT') {
                        var fio = data[messageId].client.fullName;
                    } else {
                        var fio = data[messageId].admin.fullName;
                    }

                    var d = new Date(data[messageId].dateSend);
                    var datestring = ("0" + d.getDate()).slice(-2) + "." + ("0" + (d.getMonth() + 1)).slice(-2) + "." +
                        d.getFullYear() + " " + ("0" + d.getHours()).slice(-2) + ":" + ("0" + d.getMinutes()).slice(-2);
                    messages += '<div class="message">';
                    messages += '<div class="message-header">';
                    messages += '<div class="message-sender">';
                    messages += fio;
                    messages += '</div>';
                    messages += '<div class="message-time">';
                    messages += datestring;
                    messages += '</div>';
                    messages += '</div>';
                    messages += '<div class="message-text">';
                    messages += data[messageId].message;
                    messages += '</div>';
                    messages += '</div>';
                }
                $('.chat-body')[0].innerHTML = messages;
                var objDiv = document.getElementsByClassName('chat-body')[0];
                objDiv.scrollTop = objDiv.scrollHeight;
            }
        })
    }

    $('.message-person').click(function () {
        $('.message-person').each(function (index) {
            $(this).removeClass('selected');
        });
        $(this).addClass('selected');
        getMessages();
    });

    $('.js-button-send').click(function () {
        var personId = $('.message-person.selected').attr('data');
        var currentPersonId = $('#currentPersonId').attr('data');
        var currentPersonType = $('#currentPersonType').attr('data');
        var message = $('#message-textarea').val();

        if (currentPersonType === 'CLIENT') {
            var client = currentPersonId;
            var admin = personId;
        } else {
            var client = personId;
            var admin = currentPersonId;
        }
        if (!personId || !currentPersonId || !currentPersonType || !message) {
            alert('Заполните поля для резервирования');
            return;
        }
        var params = {
            client: parseInt(client),
            admin: parseInt(admin),
            message: message,
            sender: currentPersonType === 'CLIENT' ? 'CLIENT' : 'ADMIN'
        };
        $.ajax
        ({
            dataType: "json",
            type: "POST",
            url: '/messages/send',
            data: params,
            success: function (data) {
                getMessages();
                $('#message-textarea').val('');
            },
            error: function (error) {
                console.log(error)
            }
        })
    });
});
