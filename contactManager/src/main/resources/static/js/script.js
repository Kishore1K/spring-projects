const toggleSideBar =()=>{
    if($(".sidebar").is(":visible")){
        $(".sidebar").css("display", "none");
        $(".content").css("margin-left", "0%");
    }else{
        $(".sidebar").css("display", "block");
        $(".content").css("margin-left", "20%");
    }
};

const  search =()=>{
    let query = $("#search-input").val();
    console.log(query);
    if(query===''){
        $(".search-result").hide();

    }else{
        let url = `http://localhost:8080/search/${query}`
        fetch(url).then((res)=>{
            return res.json();
        }).then((data)=>{
                console.log(data);

               let txt = `<div class='list-group' >`
                for (let datum of data) {
                    for (const datumElement of datum) {
                        txt+=`<a href="#" class="list-group-item list-group-action">${datumElement.name}</a>`
                    }

                }


                txt+=`</div>`;
                $(".search-result").html(txt).show();
            });

    }
}