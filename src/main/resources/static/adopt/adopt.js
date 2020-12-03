$(function() {
    var currentIndex = 0;
    $(document).scroll(function() {
        if ($(window).height() + $(window).scrollTop() == $(document).height()) {
            console.log('loading...');
            currentIndex += 10;
            $.ajax({
                type: "GET",
                url: contextPath + "/api/moreAdopts",
                data: {
                    currentIndex: currentIndex
                },
                timeout: 1000000,
                success: function (data) {
                  $.each(data, function() {
                    let picture = '';
                    if (this.albumFile != '') {
                        picture = '<a href="' + this.albumFile + '" data-lightbox="roadtrip"><img src="' + this.albumFile + '" width="100px" height="100px"/></a>';
                    }
                    let oneRow = '<tr><td>' + this.id + '</td><td>' + this.kind  + '</td><td>' + this.age + '</td><td>' + this.color + '</td>' +
                    '<td>' + this.sex + '</td><td>' + this.bodyType + '</td><td>' + picture + '</td><td>' + this.sterilization + '</td><td>' + this.bacterin + '</td>' +
                    '<td>' + this.status + '</td><td class="findPlace">' + this.place + '</td><td>' + this.openDate + '</td><td>' + this.shelterPhone + '</td><td class="findPlace">' + this.shelterAddress + '</td>' +
                    '<td>' + this.foundPlace + '</td><td>' + this.remark + '</td></tr>';
                    $('tbody').append(oneRow)
                  })
                },
                error: function (e) {
                  alert(e);
                }
            })
        }
    });
})

$(document).on('click', '.findPlace', function() {
    console.log($(this).text());
    window.open('https://www.google.com.tw/maps?q=' + $(this).text());
})
