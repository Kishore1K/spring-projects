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
        })
            .then((data)=>{
                console.log(data)
            })
        $(".search-result").show();
    }
}