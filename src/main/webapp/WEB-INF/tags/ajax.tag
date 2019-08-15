<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--%>
<%@attribute name="updateLink" required="true"  %>
<script type="text/javascript">
    $(function() {
        /*  Submit form using Ajax */
        $('button[type=submit]').click(function(e) {

            //Prevent default submission of form
            e.preventDefault();

            console.log( $('form[name=form]').serialize() );

            $.post({
                url : '${updateLink}',
                data : $('form[name=form]').serialize(),

                success : function(res) {


                    $('#resultContainer').html(res);
                    $('#resultContainer').show();


/*                    if(res.validated){
                        //Set response
                        $('#resultContainer pre code').text(JSON.stringify(res.employee));
                        $('#resultContainer').show();

                    }else{
                        //Set error messages
                        $.each(res.errorMessages,function(key,value){
                            $('input[name='+key+']').after('<span class="error">'+value+'</span>');
                        });
                    }*/
                },
                error: function(res) {
                    alert("error");
                    alert(res.responseText);
                    $('#resultContainer').html(res.responseText);
                    $('#resultContainer').show();
                }
            })
        });
    });
</script>