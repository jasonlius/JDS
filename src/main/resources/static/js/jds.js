var search_Btn = document.querySelector("#searchBar .button");
var searchInput = document.querySelector('#searchBar .text');
var page_up = document.getElementById("page_up")
var page_down = document.getElementById("page_down")
var inputValue;
var goods_windows = document.querySelector(".goods_windows");
var goods_template = document.getElementById("template_li");
var goods_acount 
var page_number
var max_page  
let min_page = 1

goods_template.style.display = "none"
axios.defaults.baseURL = 'http://localhost:3020';


// 监听输入框值的变化
searchInput.addEventListener('input', function() {
  // 更新后端变量的值
  inputValue = searchInput.value;
});

// 添加点击事件的处理函数
search_Btn.addEventListener('click', function() {
    if (inputValue == null){
        return
    }else{
      page_number = 0 
      get_page_callback(page_number)
    }
});

page_up.addEventListener("click",click_callback)
page_down.addEventListener("click",click_callback)


function click_callback(event){
  var clickedButton = event.target.id;
  if (clickedButton == "page_up") {
    page_number = page_number + 1
    page_down.style.backgroundColor = ""; 
    page_down.style.pointerEvents = ""; 
  } else if (clickedButton == "page_down") {
    page_number = page_number - 1
    page_up.style.backgroundColor = ""; 
    page_up.style.pointerEvents = ""; 
  }  
  if(page_number <= 0){
    event.target.style.backgroundColor = "#ccc";
    event.target.style.pointerEvents = "none";
  }else if(page_number == max_page){
    event.target.style.backgroundColor = "#ccc";
    event.target.style.pointerEvents = "none";
  }

  if((page_number <= (max_page-1) && page_number != -1) && max_page != null){
    get_page_callback(page_number)
  } else{
    return
  }  
}

function get_page_callback(page_number){
    if (page_number == 0){
      console.log("开始搜索",inputValue)
      document.getElementById("key_world_show").textContent = "“"+inputValue+"“" 
    }
    delete_old_goods_list(goods_windows)
    var url = "/goods/"+inputValue+"/"+page_number
    axios.get(url)
    .then(function(response) {
      console.log(response.data);
      goods_acount = response.data.data.totalElements
      document.getElementById("goods_acount").textContent = goods_acount
      document.getElementById("page_num").textContent = page_number+1
      max_page =  response.data.data.totalPages
      document.getElementById("all_page").textContent = max_page
      creat_goods_list_callback(response.data)
    })
    .catch(function(error) {
      console.log(error);
    });
}

function creat_goods_list_callback(data){
  if (data.flag){
    var goods_list = data.data.content 
    if(goods_list.length != 0){
        for(var i=0; i<=goods_list.length; i++ ){
          var goods = goods_list[i];   
          var new_goods_item = goods_template.cloneNode(true);
          var goods_img = new_goods_item.querySelector("img");
          var url = "https:"+goods.picPath
          goods_img.src = url
          
          new_goods_item.style.display = "block"
          new_goods_item.id = goods.id
          new_goods_item.querySelector(".goods_price_show").textContent = goods.price
          new_goods_item.querySelector(".seller_a").innerText = goods.seller
          new_goods_item.querySelector(".title_up").innerText = goods.title
          goods_windows.appendChild(new_goods_item)
        }
    }else{
      alert("没有搜索到关于“"+inputValue+"”商品信息")
    }
  }else{
    alert("程序搜索异常");
  }
}

function delete_old_goods_list(ul){
  var listItemToDelete = ul.querySelectorAll("li:not(#template_li)");
  for (var i = 0; i < listItemToDelete.length; i++) {
    ul.removeChild(listItemToDelete[i]);
  }
}



