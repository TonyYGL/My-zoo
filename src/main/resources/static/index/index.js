$(function() {

    var icons = {
          header: "ui-icon-circle-arrow-e",
          activeHeader: "ui-icon-circle-arrow-s"
        };

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


    $("#petInfoBtn").click(getInfo);
    $("#kiki").click(goHomePage);

});

let getInfo = function() {
    let contextPath = $("meta[name='ctx']").attr("content");
    $.ajax({
      url: contextPath + "/api/getPetList",
      data: {
        ownerId: 1
      },
      dataType: 'json',
      success: function(result) {
        let json = JSON.stringify(result);
        $("#result").append("<strong>" + json + "</strong>" );
      }
    });
}

let goHomePage = function() {
    alert("go home page");
}

