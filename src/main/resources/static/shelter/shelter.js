$(function() {

})

$('#mapAreaN').click(function() {
    let current = $('#mapArea').attr('src')
    let newstr = current.substring(0, current.length - 5);
    $('#mapArea').attr('src', newstr + 'N.jpg')
    getShelterList("N");
})

$('#mapAreaM').click(function() {
    let current = $('#mapArea').attr('src')
    let newstr = current.substring(0, current.length - 5);
    $('#mapArea').attr('src', newstr + 'M.jpg')
    getShelterList("M");
})

$('#mapAreaS').click(function() {
    let current = $('#mapArea').attr('src')
    let newstr = current.substring(0, current.length - 5);
    $('#mapArea').attr('src', newstr + 'S.jpg')
    getShelterList("S");
})

$('#mapAreaE').click(function() {
    let current = $('#mapArea').attr('src')
    let newstr = current.substring(0, current.length - 5);
    $('#mapArea').attr('src', newstr + 'E.jpg')
    getShelterList("E");
})

var getShelterList = function(position) {
    $('tbody').html('');
    $.ajax({
        type: 'get',
        url: contextPath + "/api/shelterList",
        data: {
            position: position
        },
        success: function (data) {
            $.each(data, function() {
                let oneRow = '<tr><td>' + this.shelterId + '</td><td>' + this.area + '</td><td>' + this.name + '</td></tr>'
                $('tbody').append(oneRow);
            })
        }
    })
}

