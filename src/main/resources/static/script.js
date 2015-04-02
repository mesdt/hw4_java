$(document).ready(function(){    
    $('#myModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var id = button.data('id') ;
        var name = button.data('name') ;
        var modal = $(this);
        modal.find('#id_input').val(id);
        modal.find('#name_input').val(name);
    });
    $("#save_button").click(function(){
            id = $("#id_input").val();
            name = $("#name_input").val();
            $.ajax({
                            type: "PUT",
                            url: "/students/"+id,
                            data: { name: name }
                    })
                    .done(function( data ) {
                            $("#message").html( '<div class="alert alert-success" role="alert">'+data+'</div>' );
                            $('#name_'+id).html(name);
                    });		
    });
    $(".btn-grades").click(function(){
            id = $(this).data("id"); 
            $("tr").removeClass('alert-info');
            $.ajax({
                            type: "GET",
                            url: "/students/"+id+"/scores"
                    })
                    .done(function( data ) {

                            $("#student-"+id).addClass('alert-info');
                            $("#grades").html( "<h3>Оценки</h3>" );
                            for (var subject in data) {
                                    $("#grades").append( subject+": "+ (data[subject] ? data[subject] : "-")+"<br>" );
                            }

                    });		
    })
 }) ;