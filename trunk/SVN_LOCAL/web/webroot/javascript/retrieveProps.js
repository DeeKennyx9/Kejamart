
function retrieveProps(){

        $.ajax({
            url: contexPath + "/searchc.html",
            success : function(data) {
                $('#result').html(data);
            }
        });
} 