$(function() {
    var icons = {
      header: "ui-icon-circle-arrow-e",
      activeHeader: "ui-icon-circle-arrow-s"
    };

    var popoverContent = '<span id="logout">登出<i class="fas fa-sign-out-alt"></i></span>';
    $('[data-toggle="popover"]').popover({
      container: 'body',
      trigger: 'focus',
      html: true,
      content: popoverContent,
      placement: 'bottom'
    })

    $('[data-toggle="popover"]').on('click', function() {
        this.focus();
    })

    $(document).on('click', '#logout', function() {
        $.ajax({
          url: contextPath + "/api/logout",
          success: function() {
            window.location.href = contextPath + '/login';
          }
        });
    });

    $("#toggle").button().on("click", function() {
      if ($("#accordion").accordion("option", "icons")) {
        $("#accordion").accordion("option", "icons", null);
      } else {
        $("#accordion").accordion("option", "icons", icons);
      }
    });

    $("#accordion").accordion({
        collapsible: true,
        icons: icons,
        header: "> div > h3"
    }).sortable({
        axis: "y",
        handle: "h3",
        stop: function(event, ui) {
            ui.item.children("h3").triggerHandler("focusout");
            $(this).accordion("refresh");
        }
    });

    loadUserInfoPage(); //初始頁面
});
const contextPath = $("meta[name='ctx']").attr("content");

var goto = function(element) {
    let href = element.getAttribute("href");
    $.ajax({
      url: href,
      success: function(data) {
        $("#content").html(data);
      },
      error: function(data) {
        $("#content").html(data.responseText);
      },
      dataType: "html"
    });
}

var loadUserInfoPage = function() {
    $.ajax({
      url: contextPath + "/menu/photoAlbum",
      success: function(data) {
        $("#content").html(data);
      },
      dataType: "html"
    });
}




