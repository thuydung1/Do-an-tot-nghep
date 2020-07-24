var myApp = angular.module("myApp", ["ngRoute"]);

myApp.config(["$routeProvider", function ($routeProvider) {
  if (!localStorage.getItem("user")) {
    $routeProvider
      .when("/login", {
        templateUrl: "views/login.html",
        controller: "loginCtrl"
      }).otherwise({
        redirectTo: "/login"
      });
  } else if (localStorage.getItem("user") === 'admin') {
    $routeProvider
      .when("/home", {  // ví dụ cái này . khi mình /home trên url thì nó sẽ chuyển sang  views/sanpham.html và cái controller 
        templateUrl: "views/trangchu.html",  // chuyển sang đây
        controller: "trangchuCtrl" // sang đây
      })
      .when("/sanpham", {  // ví dụ cái này . khi mình /home trên url thì nó sẽ chuyển sang  views/sanpham.html và cái controller 
        templateUrl: "views/sanpham.html",  // chuyển sang đây
        controller: "sanphamCtrl" // sang đây
      })
      .when("/sanphams", {
        templateUrl: "views/sanpham.html",
        controller: "sanphamCtrl"
      })
      .when("/nhacungcaps", {
        templateUrl: "views/nhacungcap.html",
        controller: "nhacungcapCtrl" // cái này cgh chỉnh nè
      })

      .when("/nhanviens", {
        templateUrl: "views/nhanvien.html",
        controller: "nhanvienCtrl"
      })
      .when("/khachhangs", {
        templateUrl: "views/khachhang.html",
        controller: "khachhangCtrl"
      })

      .when("/phanloais", {
        templateUrl: "views/phanloai.html",
        controller: "phanloaiCtrl"

      })
      .when("/phieunhapkhos", {
        templateUrl: "views/phieunhapkho.html",
        controller: "phieunhapkhoCtrl"
      })
      .when("/phieuxuatkhos", {
        templateUrl: "views/phieuxuatkho.html",
        controller: "phieuxuatkhoCtrl"
      })
      .when("/hangtonkho", {
        templateUrl: "views/hangtonkho.html",
        controller: "hangtonkhoCtrl"
      })

      .otherwise({
        redirectTo: "/home"
      });
  } else if (localStorage.getItem("user") === 'user') {
    $routeProvider
      .when("/fobbiden", {
        templateUrl: "views/fobbiden.html",
      })
      .when("/phieuxuatkhos", {
        templateUrl: "views/phieuxuatkho.html",
        controller: "phieuxuatkhoCtrl"
      })
      .when("/phieunhapkhos", {
        templateUrl: "views/phieunhapkho.html",
        controller: "phieunhapkhoCtrl"
      })
      .when("/khachhangs", {
        templateUrl: "views/khachhang.html",
        controller: "khachhangCtrl"
      })
      .when("/hangtonkho", {
        templateUrl: "views/hangtonkho.html",
        controller: "hangtonkhoCtrl"
      })
      .otherwise({
        redirectTo: "/fobbiden"
      });
  }
}]);

function ymd(inputString)//Chuyển định dạng sang năm tháng ngày
{
  var year = inputString.substring(0, 4);//Lấy năm của đầu sách
  var month = inputString.substring(5, 7);//Lấy tháng của đầu sách
  var day = inputString.substring(8, 10);//Lấy ngày của đầu sách
  var myDate = month + "/" + day + "/" + year;//Định dạng yyyy-MM-dd
  return myDate;
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
myApp.controller('trangchuCtrl', function ($scope, $http) {
  $http({
    method: "GET",
    url: "http://localhost:8080/tongquan"
  }).then(function mySuccess(response) {
    console.log(response.data)
    $scope.tongquan = response.data;
  }, function myError(response) {
    $scope.sanphams = response.statusText;
  });
});
myApp.controller('loginCtrl', function ($scope, $http) {
  $scope.dangnhap = function (username, matkhau)//Lưu lúc thêm
  {
    var data =
    {
      username: username,
      matkhau: matkhau,
    };
    if ((username === 'admin' && matkhau === "admin") || username === 'user' && matkhau === "user") {
      localStorage.setItem("user", username);
      location.reload();
    }
    //   $http.post('http://localhost:8080/addkhachhang', JSON.stringify(data)).then(function (response) {
    //     if (response.data) {
    //       alert("Đã thêm mới ");
    //     }
    //   }, function (response) {
    //     $scope.bookTitles = response.statusText;
    //   });
    // };
  }
});
myApp.controller('sanphamCtrl', function ($scope, $http) {
  //Lấy dữ liệu dạng JSON ở server bằng phương thức $http.get
  // LẤY LINK OR POSTMAN BO DO
  // này là lấy tất cả sản phẩm r nè
  $http({
    method: "GET",
    url: "http://localhost:8080/sanphams"
  }).then(function mySuccess(response) {
    $scope.sanphams = response.data;
  }, function myError(response) {
    $scope.sanphams = response.statusText;
  });
  /// giờ là lấy nhà cung cấp. lấy để khi mà mih nhập dữ liệu, nó sẽ hiện ra các nhà cung cấp để mình chọn
  // chứ khi ngta nhập ngta đâu có nhớ mã đâu 

  $http({
    method: "GET",
    url: "http://localhost:8080/nhacungcaps"
  }).then(function mySuccess(response) {
    $scope.nhacungcaps = response.data;
  }, function myError(response) {
    $scope.nhacungcaps = response.statusText;
  });

  $http({
    method: "GET",
    url: "http://localhost:8080/phanloais"
  }).then(function mySuccess(response) {
    $scope.phanloais = response.data;
  }, function myError(response) {
    $scope.phanloais = response.statusText;
  });

  $scope.themSanPham = function ()//Khi bấm vào nút THÊM
  {
    $scope.modalTitle = "THÊM SẢN PHẨM ";//Thay đổi tiêu đề của modal
    $scope.adding = true;//Đang thêm = true
    $scope.modifying = false;//Đang sửa = false
    $scope.disabled = true;//enable id_ISBN

    //Khi thêm mới thì các field trong form phải trống
    $scope.id_SP = "";
    $scope.tenSP = "";
    $scope.id_NCC = "";
    $scope.soluong_SP = "";
    $scope.ngaynhap_ma = "";
    $scope.giaban_SP = "";
    $scope.ngaysx_SP = "";
    $scope.hansd_SP = "";
    // document.getElementById("ngaynhap_ma").value = "";
    // document.getElementById("ngaysx_SP").value = "";
    // ocument.getElementById("hansd_SP").value = "";

    $scope.id_LSP = "";

  };


  $scope.addsanpham = function (id_SP, tenSP, id_NCC, soluong_SP, giaban_SP, ngaynhap_ma, ngaysx_SP, hansd_SP, id_LSP, hinhAnh)//Lưu lúc thêm
  {
    debugger
    if (tenSP == "" || id_NCC == "" || soluong_SP == "" || giaban_SP == "" ||
      ngaynhap_ma == null || ngaysx_SP == "" || hansd_SP == "" || id_LSP == null) {
      alert("Vui lòng nhập những thông tin cần thiết!");
      $scope.save1 = "";//Nhập thiếu thì form không biến mất
      return;
    }
    else {
      $scope.save1 = "modal";//Nhập đủ thì lưu lại và form biến mất
      let hinhanhUrl = document.getElementById("imgProduct").files.length>0?document.getElementById("imgProduct").files[0].name:"";
      var data =
      {
        tenSP: tenSP,
        nhacungcap: {
          id_NCC: id_NCC,
          tenNCC: "",
          sdtNCC: "",
          diachiNCC: "",
          stkNCC: ""
        },
        soluong_SP: soluong_SP,
        giaban_SP: giaban_SP,
        ngaynhap_ma: ngaynhap_ma,
        ngaysx_SP: ngaysx_SP,
        hansd_SP: hansd_SP,
        hinhanh_SP: hinhanhUrl,
        phanloai: {
          id_LSP: id_LSP,
          tenLSP: ""
        }
      };

      $http.post('http://localhost:8080/addsanpham', JSON.stringify(data)).then(function (response) {
        if (response.data) {
          alert("Đã thêm mới ");
        }
      }, function (response) {
        $scope.bookTitles = response.statusText;
      });
    }
    // alert("Hello");
  };

  $scope.editsanpham = function (sanpham)//Khi bấm vào biểu tượng sửa
  {

    $scope.modalTitle = "CẬP NHẬT SẢN PHẨM";//Thay đổi tiêu đề của modal
    $scope.adding = false;//Đang thêm = false
    $scope.modifying = true;//Đang sửa = true
    $scope.disabled = true;//disable id_ISBN không cho sửa


    $scope.id_SP = sanpham.id_SP;
    $scope.tenSP = sanpham.tenSP;
    document.getElementById("id_NCC").value = sanpham.nhacungcap.id_NCC; // khóa ngoại thì kiểu này
    $scope.soluong_SP = sanpham.soluong_SP;
    $scope.giaban_SP = sanpham.giaban_SP;

    // var myDate = ymd(sanpham.ngaynhap_SP);
    // document.getElementById("ngaynhap_SP").value = myDate;//Muốn gán giá trị cho date input thì bắt buộc phải là định dạng yyyy-MM-dd
    //  var finalDate = new Date(myDate);
    //  $scope.ngaynhap_SP = finalDate;
    // alert(myDate);

    $scope.ngaynhap_ma = new Date(sanpham.ngaynhap_ma); // này copy ra xàii. okok
    $scope.ngaysx_SP = new Date(sanpham.ngaysx_SP);
    $scope.hansd_SP = new Date(sanpham.hansd_SP);

    document.getElementById("id_LSP").value = sanpham.phanloai.id_LSP;
    $scope.hinhanh_SP = sanpham.hinhanh_SP;


  };

  //Sửa dữ liệu JSON ở server bằng phương thức $http.put
  $scope.updatesanpham = function (id_SP, tenSP, id_NCC, soluong_SP, giaban_SP, ngaynhap_ma, ngaysx_SP, hansd_SP, id_LSP)//Lưu lúc sửa
  {
    let hinhanhUrl = document.getElementById("imgProduct").files.length>0?document.getElementById("imgProduct").files[0].name:"";
    $scope.save2 = "modal";//Nhập đủ thì lưu lại và form biến mất

    var data =
    {
      id_SP: id_SP,
      tenSP: tenSP,
      nhacungcap: {
        id_NCC: document.getElementById("id_NCC").value,
        tenNCC: "",
        sdtNCC: "",
        diachiNCC: "",
        stkNCC: ""
      },
      soluong_SP: soluong_SP,
      giaban_SP: giaban_SP,
      ngaynhap_ma: ngaynhap_ma,
      ngaysx_SP: ngaysx_SP,
      hansd_SP: hansd_SP,
      hinhanh_SP: hinhanhUrl,
      phanloai: {
        id_LSP: document.getElementById("id_LSP").value,
        tenLSP: ""
      }
    };

    $http.put('http://localhost:8080/updatsanpham/' + id_SP, JSON.stringify(data)).then(function (response) {
      if (response.data) {
        alert("Đã cập nhật !");
        location.reload();
      }
    }, function (response) {
      alert("Sửa thất bại!");
    });
  };

  //Xóa dữ liệu JSON ở server bằng phương thức $http.delete
  $scope.deletesanpham = function (sanpham)////Khi bấm vào nút DELETE
  {
    var id = sanpham.id_SP;
    var r = confirm("Bạn Chắc Chắn Xóa ?");
    if (r == true) {
      $http({
        method: 'DELETE',
        url: 'http://localhost:8080/delsanpham/' + id
      })
      alert("Xóa Thành Công");
      // window.location.reload();
    }
    else {
      alert("Xóa Thất Bại");
      // window.location.reload();
    }
  };

  $scope.chooseImg = function (sanpham) {
    $scope.readyUpload = true;
  }

  $scope.updateImg = function (sanpham) {
    var url = document.getElementById("imgUpdate").value;
    var fileName = url.substring(url.lastIndexOf("\\") + 1);

    var data =
    {
      id_SP: id_SP,
      tenSP: tenSP,
      nhacungcap: {
        id_NCC: document.getElementById("id_NCC").value,
        tenNCC: "",
        sdtNCC: "",
        diachiNCC: "",
        stkNCC: ""
      },
      soluong_SP: soluong_SP,
      giaban_SP: giaban_SP,
      ngaynhap_SP: ngaynhap_SP,
      ngaysx_SP: ngaysx_SP,
      hansd_SP: hansd_SP,
      hinhanh_SP: hinhanh_SP,
      phanloai: {
        id_LSP: document.getElementById("id_LSP").value,
        tenLSP: ""
      }
    };

    $http.put('http://localhost:8080/updatsanpham/' + sanpham.id_SP, JSON.stringify(data)).then(function (response) {
      if (response.data) {
        alert("Đã cập nhật lại hình ảnh đầu sách " + sanpham.id_SP + "!");
      }
    }, function (response) {
      alert("Lỗi cập nhật hình ảnh!");
    });
  }
});

myApp.controller('hangtonkhoCtrl', function ($scope, $http) {
  $http({
    method: "GET",
    url: "http://localhost:8080/hangTonKho"
  }).then(function mySuccess(response) {
    $scope.hangTonKho = response.data;
  }, function myError(response) {
    $scope.hangTonKho = response.statusText;
  });
  $scope.showDetailModal = function (sanpham) {
    $scope.modalTitle = "Thông tin sản phẩm";
    $scope.tenSP = sanpham.tenSP;
    $scope.ngaysx_SP = sanpham.ngaysx_SP;
    $scope.hansd_SP = sanpham.hansd_SP;
    $scope.giaban_SP = sanpham.giaban_SP;
    $scope.soluong_SP = sanpham.soluong_SP;
    $scope.nhacungcap = sanpham.nhacungcap.tenNCC;
    $scope.phanloai = sanpham.phanloai.tenLSP;
  };

});

myApp.controller('nhacungcapCtrl', function ($scope, $http) {

  $http({
    method: "GET",
    url: "http://localhost:8080/nhacungcaps"
  }).then(function mySuccess(response) {
    $scope.nhacungcaps = response.data;
  }, function myError(response) {
    $scope.nhacungcaps = response.statusText;
  });

  $scope.createBookTitle = function ()//Khi bấm vào nút THÊM
  {
    $scope.modalTitle = "THÊM NHÀ CUNG CẤP ";//Thay đổi tiêu đề của modal
    $scope.adding = true;//Đang thêm = true
    $scope.modifying = false;//Đang sửa = false
    $scope.disabled = true;//enable id_ISBN

    //Khi thêm mới thì các field trong form phải trống
    $scope.id_NCC = "";
    $scope.tenNCC = "";
    $scope.sdtNCC = "";
    $scope.diachiNCC = "";
    $scope.stkNCC = "";
  };
  //Lấy dữ liệu dạng JSON ở server bằng phương thức $http.post
  $scope.addnhacungcap = function (id_NCC, tenNCC, sdtNCC, diachiNCC, stkNCC)//Lưu lúc thêm /// chỉnh ỏ d89a6y
  {

    if (tenNCC == "" || sdtNCC == "" || diachiNCC == "" || stkNCC == "") {
      alert("Vui lòng nhập những thông tin cần thiết!");
      $scope.save1 = "";//Nhập thiếu thì form không biến mất
      return;
    }
    else {
      $scope.save1 = "modal";//Nhập đủ thì lưu lại và form biến mất
      //Lấy tên hình-

      var data =
      {
        tenNCC: tenNCC,
        sdtNCC: sdtNCC,
        diachiNCC: diachiNCC,
        stkNCC: stkNCC,
      };

      $http.post('http://localhost:8080/addnhacungcap', JSON.stringify(data)).then(function (response) {
        if (response.data) {
          alert("Đã thêm mới ");
        }
      }, function (response) {
        $scope.bookTitles = response.statusText;
      });
    }
  };

  $scope.editnhacungcap = function (nhacungcap)//Khi bấm vào biểu tượng sửa
  {

    $scope.modalTitle = "CẬP NHẬT NHÀ CUNG CẤP";//Thay đổi tiêu đề của modal
    $scope.adding = false;//Đang thêm = false
    $scope.modifying = true;//Đang sửa = true
    $scope.disabled = true;//disable id_ISBN không cho sửa

    $scope.id_NCC = nhacungcap.id_NCC;
    $scope.tenNCC = nhacungcap.tenNCC;
    $scope.sdtNCC = nhacungcap.sdtNCC; // nếu là bumber thì bỏ parseint vào là dc . k cần đỏi qua text
    $scope.diachiNCC = nhacungcap.diachiNCC;
    $scope.stkNCC = nhacungcap.stkNCC;
  };

  //Sửa dữ liệu JSON ở server bằng phương thức $http.put
  $scope.updatencc = function (id_NCC, tenNCC, sdtNCC, diachiNCC, stkNCC)//Lưu lúc sửa /// này là cái nút lưu /// lấy cái này làm mãu nha
  {


    $scope.save2 = "modal";//Nhập đủ thì lưu lại và form biến mất
    var data =
    {
      id_NCC: id_NCC,
      tenNCC: tenNCC,
      sdtNCC: sdtNCC,
      diachiNCC: diachiNCC,
      stkNCC: stkNCC
    };

    $http.put('http://localhost:8080/updatenhacungcap/' + id_NCC, JSON.stringify(data)).then(function (response) {
      if (response.data) {
        alert("Đã cập nhật !");
        location.reload();
      }
    }, function (response) {
      alert("Sửa thất bại!");
    });

  };

  //Xóa dữ liệu JSON ở server bằng phương thức $http.delete
  $scope.deletenhacungcap = function (nhacungcap)////Khi bấm vào nút DELETE
  {
    var id = nhacungcap.id_NCC;
    var r = confirm("Bạn Chắc Chắn Xóa ?");
    if (r == true) {
      $http({
        method: 'DELETE',
        url: 'http://localhost:8080/delnhacungcap/' + id
      })
      alert("Xóa Thành Công");
      // window.location.reload();
    }
    else {
      alert("Xóa Thất Bại");
      // window.location.reload();
    }
  };

  $scope.chooseImg = function () {
    $scope.readyUpload = true;
  }

  $scope.updateImg = function (bookTitle) {
    var url = document.getElementById("imgUpdate").value;
    var fileName = url.substring(url.lastIndexOf("\\") + 1);

    var data =
    {
      id_ISBN: bookTitle.id_ISBN,
      id_LoaiSach: bookTitle.id_LoaiSach,
      publisher: {
        id_NXB: bookTitle.publisher.id_NXB,
        tenNXB: "",
        diaChiNXB: "",
        sdtNXB: "",
        emailNXB: ""
      },
      tenDS: bookTitle.tenDS,
      tomLuocNoiDung: bookTitle.tomLuocNoiDung,
      khoSach: bookTitle.khoSach,
      soTrang: bookTitle.soTrang,
      dinhKem: bookTitle.dinhKem,
      viTri: bookTitle.viTri,
      ngonNgu: bookTitle.ngonNgu,
      phienBan: bookTitle.phienBan,
      namXuatBan: bookTitle.namXuatBan,
      hinhAnh: fileName
    };

    $http.put('http://localhost:8080/updateBookTitle/' + bookTitle.id_ISBN, JSON.stringify(data)).then(function (response) {
      if (response.data) {
        alert("Đã cập nhật lại hình ảnh đầu sách " + bookTitle.id_ISBN + "!");
      }
    }, function (response) {
      alert("Lỗi cập nhật hình ảnh!");
    });
  }
});

myApp.controller('nhanvienCtrl', function ($scope, $http) {
  //Lấy dữ liệu dạng JSON ở server bằng phương thức $http.get
  // LẤY LINK OR POSTMAN BO DO
  // này là lấy tất cả sản phẩm r nè

  // sửa chỗ get này 
  $http({
    method: "GET",
    url: "http://localhost:8080/nhanviens"
  }).then(function mySuccess(response) {
    $scope.nhanviens = response.data;
  }, function myError(response) {
    $scope.nhanviens = response.statusText;
  });

  /// giờ là lấy nhà cung cấp. lấy để khi mà mih nhập dữ liệu, nó sẽ hiện ra các nhà cung cấp để mình chọn
  // chứ khi ngta nhập ngta đâu có nhớ mã đâu 


  $scope.createBookTitle = function ()//Khi bấm vào nút THÊM
  {
    $scope.modalTitle = "THÊM NHÂN VIÊN ";//Thay đổi tiêu đề của modal
    $scope.adding = true;//Đang thêm = true
    $scope.modifying = false;//Đang sửa = false
    $scope.disabled = true;//enable id_ISBN

    //Khi thêm mới thì các field trong form phải trống
    $scope.id_NV = "";
    $scope.tenNV = "";
    $scope.gioitinhNV = "";
    $scope.chucvuNV = "";
    $scope.cmndNV = "";
    $scope.quequanNV = "";
    $scope.stkNV = "";
    $scope.sdtNV = "";
    $scope.diachiNV = "";
    document.getElementById("ngaysinhNV").value = "";




  };

  //Lấy dữ liệu dạng JSON ở server bằng phương thức $http.post
  $scope.addnhanvien = function (id_NV, tenNV, gioitinhNV, chucvuNV, ngaysinhNV, cmndNV, quequanNV, stkNV, sdtNV, diachiNV, HinhAnh)//Lưu lúc thêm
  {

    if (tenNV == "" || gioitinhNV == "" || chucvuNV == "" || ngaysinhNV == "" || cmndNV == "" || quequanNV == "" ||
      stkNV == "" || sdtNV == "" || diachiNV == "" || HinhAnh == "") {
      alert("Vui lòng nhập những thông tin cần thiết!");
      $scope.save1 = "";//Nhập thiếu thì form không biến mất
      return;
    }
    else {
      $scope.save1 = "modal";//Nhập đủ thì lưu lại và form biến mất
      //Lấy tên hình-
      var url = document.getElementById("imgUpdate").value;
      var fileName = url.substring(url.lastIndexOf("\\") + 1);
      let hinhanhNhanVien = document.getElementById("imgNhanVien").files.length>0?
      document.getElementById("imgNhanVien").files[0].name:"";
      var data =
      {
        tenNV: tenNV,
        gioitinhNV: gioitinhNV,
        chucvuNV: chucvuNV,
        ngaysinhNV: ngaysinhNV,
        cmndNV: cmndNV,
        quequanNV: quequanNV,
        stkNV: stkNV,
        sdtNV: sdtNV,
        diachiNV: diachiNV,
        hinhAnh: hinhanhNhanVien
      };

      $http.post('http://localhost:8080/addnhanvien', JSON.stringify(data)).then(function (response) {
        if (response.data) {
          alert("Đã thêm mới ");
        }
      }, function (response) {
        $scope.bookTitles = response.statusText;
      });
    }
  };

  $scope.editnhanvien = function (nhanvien)//Khi bấm vào biểu tượng sửa
  {

    $scope.modalTitle = "CẬP NHẬT NHÂN VIÊN";//Thay đổi tiêu đề của modal
    $scope.adding = false;//Đang thêm = false
    $scope.modifying = true;//Đang sửa = true
    $scope.disabled = true;//disable id_ISBN không cho sửa

    $scope.id_NV = nhanvien.id_NV;
    $scope.tenNV = nhanvien.tenNV;
    $scope.gioitinhNV = nhanvien.gioitinhNV;
    $scope.chucvuNV = nhanvien.chucvuNV;
    $scope.ngaysinhNV = new Date(nhanvien.ngaysinhNV);
    $scope.cmndNV = nhanvien.cmndNV;
    $scope.quequanNV = nhanvien.quequanNV;
    $scope.stkNV = nhanvien.stkNV;
    $scope.sdtNV = nhanvien.sdtNV;
    $scope.diachiNV = nhanvien.diachiNV;
    $scope.HinhAnh = nhanvien.HinhAnh;
  };

  //Sửa dữ liệu JSON ở server bằng phương thức $http.put
  $scope.updatenhanvien = function (id_NV, tenNV, gioitinhNV, chucvuNV, ngaysinhNV, cmndNV, quequanNV, stkNV, sdtNV, diachiNV, HinhAnh)//Lưu lúc sửa
  {
    $scope.save2 = "modal";//Nhập đủ thì lưu lại và form biến mất
    let hinhanhNhanVien = document.getElementById("imgNhanVien").files.length>0?
    document.getElementById("imgNhanVien").files[0].name:"";
    var data =
    {
      id_NV: id_NV,
      tenNV: tenNV,
      gioitinhNV: gioitinhNV,
      chucvuNV: chucvuNV,
      ngaysinhNV: ngaysinhNV,
      cmndNV: cmndNV,
      quequanNV: quequanNV,
      stkNV: stkNV,
      sdtNV: sdtNV,
      diachiNV: diachiNV,
      hinhAnh: hinhanhNhanVien
    };

    $http.put('http://localhost:8080/updatnhanvien/' + id_NV, JSON.stringify(data)).then(function (response) {
      if (response.data) {
        alert("Đã cập nhật !");
        location.reload();
      }
    }, function (response) {
      alert("Sửa thất bại!");
    });
  };

  //Xóa dữ liệu JSON ở server bằng phương thức $http.delete
  $scope.deletenhanvien = function (nhanvien)////Khi bấm vào nút DELETE
  {
    var id = nhanvien.id_NV;
    var r = confirm("Bạn Chắc Chắn Xóa ?");
    if (r == true) {
      $http({
        method: 'DELETE',
        url: 'http://localhost:8080/delnhanvien/' + id
      })
      alert("Xóa Thành Công");
      // window.location.reload();
    }
    else {
      alert("Xóa Thất Bại");
      // window.location.reload();
    }
  };

  $scope.chooseImg = function () {
    $scope.readyUpload = true;
  }

  $scope.updateImg = function (bookTitle) {
    var url = document.getElementById("imgUpdate").value;
    var fileName = url.substring(url.lastIndexOf("\\") + 1);

    var data =
    {
      id_ISBN: bookTitle.id_ISBN,
      id_LoaiSach: bookTitle.id_LoaiSach,
      publisher: {
        id_NXB: bookTitle.publisher.id_NXB,
        tenNXB: "",
        diaChiNXB: "",
        sdtNXB: "",
        emailNXB: ""
      },
      tenDS: bookTitle.tenDS,
      tomLuocNoiDung: bookTitle.tomLuocNoiDung,
      khoSach: bookTitle.khoSach,
      soTrang: bookTitle.soTrang,
      dinhKem: bookTitle.dinhKem,
      viTri: bookTitle.viTri,
      ngonNgu: bookTitle.ngonNgu,
      phienBan: bookTitle.phienBan,
      namXuatBan: bookTitle.namXuatBan,
      hinhAnh: fileName
    };

    $http.put('http://localhost:8080/updateBookTitle/' + bookTitle.id_ISBN, JSON.stringify(data)).then(function (response) {
      if (response.data) {
        alert("Đã cập nhật lại hình ảnh đầu sách " + bookTitle.id_ISBN + "!");
      }
    }, function (response) {
      alert("Lỗi cập nhật hình ảnh!");
    });
  }
});

myApp.controller('khachhangCtrl', function ($scope, $http) {

  $http({
    method: "GET",
    url: "http://localhost:8080/khachhangs"
  }).then(function mySuccess(response) {
    $scope.khachhangs = response.data;
  }, function myError(response) {
    $scope.khachhangs = response.statusText;
  });

  $scope.createBookTitle = function ()//Khi bấm vào nút THÊM
  {
    $scope.modalTitle = "THÊM KHÁCH HÀNG ";//Thay đổi tiêu đề của modal
    $scope.adding = true;//Đang thêm = true
    $scope.modifying = false;//Đang sửa = false
    $scope.disabled = true;//enable id_ISBN

    //Khi thêm mới thì các field trong form phải trống
    $scope.id_KH = "";
    $scope.tenKH = "";
    $scope.sdtKH = "";
    $scope.diachiKH = "";
    $scope.stkKH = "";
  };
  $scope.addkhachhang = function (id_KH, tenKH, sdtKH, diachiKH, stkKH)//Lưu lúc thêm
  {

    if (tenKH == "" || sdtKH == "" || diachiKH == "" || stkKH == "") {
      alert("Vui lòng nhập những thông tin cần thiết!");
      $scope.save1 = "";//Nhập thiếu thì form không biến mất
      return;
    }
    else {
      $scope.save1 = "modal";//Nhập đủ thì lưu lại và form biến mất
      //Lấy tên hình-

      var data =
      {
        tenKH: tenKH,
        sdtKH: sdtKH,
        diachiKH: diachiKH,
        stkKH: stkKH,
      };

      $http.post('http://localhost:8080/addkhachhang', JSON.stringify(data)).then(function (response) {
        if (response.data) {
          alert("Đã thêm mới ");
        }
      }, function (response) {
        $scope.bookTitles = response.statusText;
      });
    }
  };

  $scope.editkhachhang = function (khachhang)//Khi bấm vào biểu tượng sửa
  {

    $scope.modalTitle = "CẬP NHẬT KHÁCH HÀNG";//Thay đổi tiêu đề của modal
    $scope.adding = false;//Đang thêm = false
    $scope.modifying = true;//Đang sửa = true
    $scope.disabled = true;//disable id_ISBN không cho sửa

    $scope.id_KH = khachhang.id_KH;
    $scope.tenKH = khachhang.tenKH;
    $scope.sdtKH = khachhang.sdtKH;
    $scope.diachiKH = khachhang.diachiKH;
    $scope.stkKH = khachhang.stkKH;

  };

  //Sửa dữ liệu JSON ở server bằng phương thức $http.put

  $scope.updateKH = function (id_KH, tenKH, sdtKH, diachiKH, stkKH)//Lưu lúc sửa
  {

    $scope.save2 = "modal";//Nhập đủ thì lưu lại và form biến mất
    var data =
    {
      id_KH: id_KH,
      tenKH: tenKH,
      sdtKH: sdtKH,
      diachiKH: diachiKH,
      stkKH: stkKH
    };

    $http.put('http://localhost:8080/updatekhachhang/' + id_KH, JSON.stringify(data)).then(function (response) {
      if (response.data) {
        alert("Đã cập nhật !");
        location.reload();
      }
    }, function (response) {
      alert("Sửa thất bại!");
    });
  };

  //Xóa dữ liệu JSON ở server bằng phương thức $http.delete
  $scope.deletekhachhang = function (khachhang)////Khi bấm vào nút DELETE
  {
    var id = khachhang.id_KH;
    var r = confirm("Bạn Chắc Chắn Xóa ?");
    if (r == true) {
      $http({
        method: 'DELETE',
        url: 'http://localhost:8080/delkhachhang/' + id
      })
      alert("Xóa Thành Công");
      // window.location.reload();
    }
    else {
      alert("Xóa Thất Bại");
      // window.location.reload();
    }
  };

  $scope.chooseImg = function () {
    $scope.readyUpload = true;
  }

  $scope.updateImg = function (bookTitle) {
    var url = document.getElementById("imgUpdate").value;
    var fileName = url.substring(url.lastIndexOf("\\") + 1);

    var data =
    {
      id_ISBN: bookTitle.id_ISBN,
      id_LoaiSach: bookTitle.id_LoaiSach,
      publisher: {
        id_NXB: bookTitle.publisher.id_NXB,
        tenNXB: "",
        diaChiNXB: "",
        sdtNXB: "",
        emailNXB: ""
      },
      tenDS: bookTitle.tenDS,
      tomLuocNoiDung: bookTitle.tomLuocNoiDung,
      khoSach: bookTitle.khoSach,
      soTrang: bookTitle.soTrang,
      dinhKem: bookTitle.dinhKem,
      viTri: bookTitle.viTri,
      ngonNgu: bookTitle.ngonNgu,
      phienBan: bookTitle.phienBan,
      namXuatBan: bookTitle.namXuatBan,
      hinhAnh: fileName
    };

    $http.put('http://localhost:8080/updateBookTitle/' + bookTitle.id_ISBN, JSON.stringify(data)).then(function (response) {
      if (response.data) {
        alert("Đã cập nhật lại hình ảnh đầu sách " + bookTitle.id_ISBN + "!");
      }
    }, function (response) {
      alert("Lỗi cập nhật hình ảnh!");
    });
  }
});

myApp.controller('phanloaiCtrl', function ($scope, $http) {

  $http({
    method: "GET",
    url: "http://localhost:8080/phanloais"
  }).then(function mySuccess(response) {
    $scope.phanloais = response.data;
  }, function myError(response) {
    $scope.phanloais = response.statusText;
  });

  $scope.createBookTitle = function ()//Khi bấm vào nút THÊM
  {
    $scope.modalTitle = "THÊM LOẠI SẢN PHẨM ";//Thay đổi tiêu đề của modal
    $scope.adding = true;//Đang thêm = true
    $scope.modifying = false;//Đang sửa = false
    $scope.disabled = true;//enable id_ISBN

    //Khi thêm mới thì các field trong form phải trống
    $scope.id_LSP = "";
    $scope.tenLSP = "";
  };
  $scope.addphanloai = function (id_LSP, tenLSP)//Lưu lúc thêm
  {

    if (tenLSP == "") {
      alert("Vui lòng nhập những thông tin cần thiết!");
      $scope.save1 = "";//Nhập thiếu thì form không biến mất
      return;
    }
    else {
      $scope.save1 = "modal";//Nhập đủ thì lưu lại và form biến mất


      var data =
      {
        tenLSP: tenLSP,
      };

      $http.post('http://localhost:8080/addphanloai', JSON.stringify(data)).then(function (response) {
        if (response.data) {
          alert("Đã thêm mới ");
        }
      }, function (response) {
        $scope.bookTitles = response.statusText;
      });
    }
  };

  $scope.editphanloai = function (phanloai)//Khi bấm vào biểu tượng sửa
  {

    $scope.modalTitle = "CẬP NHẬT PHÂN LOẠI";//Thay đổi tiêu đề của modal
    $scope.adding = false;//Đang thêm = false
    $scope.modifying = true;//Đang sửa = true
    $scope.disabled = true;//disable id_ISBN không cho sửa

    $scope.id_LSP = phanloai.id_LSP;
    $scope.tenLSP = phanloai.tenLSP;

  };

  //Sửa dữ liệu JSON ở server bằng phương thức $http.put
  $scope.updatephanloai = function (id_LSP, tenLSP)//Lưu lúc sửa
  {

    $scope.save2 = "modal";//Nhập đủ thì lưu lại và form biến mất


    var data =
    {
      id_LSP: id_LSP,
      tenLSP: tenLSP
    };

    $http.put('http://localhost:8080/updatphanloai/' + id_LSP, JSON.stringify(data)).then(function (response) {
      if (response.data) {
        alert("Đã cập nhật !");
        location.reload();
      }
    }, function (response) {
      alert("Sửa thất bại!");
    });
  };

  //Xóa dữ liệu JSON ở server bằng phương thức $http.delete
  $scope.deletephanloai = function (phanloai)////Khi bấm vào nút DELETE
  {
    var id = phanloai.id_LSP;
    var r = confirm("Bạn Chắc Chắn Xóa ?");
    if (r == true) {
      $http({
        method: 'DELETE',
        url: 'http://localhost:8080/delphanloai/' + id
      })
      alert("Xóa Thành Công");
      // window.location.reload();
    }
    else {
      alert("Xóa Thất Bại");
      // window.location.reload();
    }
  };

  $scope.chooseImg = function () {
    $scope.readyUpload = true;
  }

  $scope.updateImg = function (bookTitle) {
    var url = document.getElementById("imgUpdate").value;
    var fileName = url.substring(url.lastIndexOf("\\") + 1);

    var data =
    {
      id_ISBN: bookTitle.id_ISBN,
      id_LoaiSach: bookTitle.id_LoaiSach,
      publisher: {
        id_NXB: bookTitle.publisher.id_NXB,
        tenNXB: "",
        diaChiNXB: "",
        sdtNXB: "",
        emailNXB: ""
      },
      tenDS: bookTitle.tenDS,
      tomLuocNoiDung: bookTitle.tomLuocNoiDung,
      khoSach: bookTitle.khoSach,
      soTrang: bookTitle.soTrang,
      dinhKem: bookTitle.dinhKem,
      viTri: bookTitle.viTri,
      ngonNgu: bookTitle.ngonNgu,
      phienBan: bookTitle.phienBan,
      namXuatBan: bookTitle.namXuatBan,
      hinhAnh: fileName
    };

    $http.put('http://localhost:8080/updateBookTitle/' + bookTitle.id_ISBN, JSON.stringify(data)).then(function (response) {
      if (response.data) {
        alert("Đã cập nhật lại hình ảnh đầu sách " + bookTitle.id_ISBN + "!");
      }
    }, function (response) {
      alert("Lỗi cập nhật hình ảnh!");
    });
  }
});

myApp.controller('phieunhapkhoCtrl', function ($scope, $http) {

  $http({
    method: "GET",
    url: "http://localhost:8080/phieunhapkhos"
  }).then(function mySuccess(response) {
    $scope.phieunhapkhos = response.data;
  }, function myError(response) {
    $scope.phieunhapkhos = response.statusText;
  });


  $http({
    method: "GET",
    url: "http://localhost:8080/sanphams"
  }).then(function mySuccess(response) {
    $scope.sanphams = response.data;
  }, function myError(response) {
    $scope.sanphams = response.statusText;
  });

  $http({
    method: "GET",
    url: "http://localhost:8080/nhanviens"
  }).then(function mySuccess(response) {
    $scope.nhanviens = response.data;
  }, function myError(response) {
    $scope.nhanviens = response.statusText;
  });

  $http({
    method: "GET",
    url: "http://localhost:8080/nhacungcaps"
  }).then(function mySuccess(response) {
    $scope.nhacungcaps = response.data;
  }, function myError(response) {
    $scope.nhacungcaps = response.statusText;
  });

  $http({
    method: "GET",
    url: "http://localhost:8080/phanloais"
  }).then(function mySuccess(response) {
    $scope.phanloais = response.data;
  }, function myError(response) {
    $scope.phanloais = response.statusText;
  });

  $scope.createPhieuNhapKho = function ()//Khi bấm vào nút THÊM
  {
    $scope.modalTitle = "THÊM PHIẾU NHẬP  ";//Thay đổi tiêu đề của modal
    $scope.adding = true;//Đang thêm = true
    $scope.modifying = false;//Đang sửa = false
    $scope.disabled = true;//enable id_ISBN

    //Khi thêm mới thì các field trong form phải trống
    $scope.id_PN = "";
    $scope.tenPN = "";
    $scope.id_SP = "";
    $scope.id_NV = "";
    $scope.soluong = "";
    $scope.gianhap = "";
    $scope.id_NCC = "";
    $scope.id_LSP = "";
    $scope.mangaynhap = "";
    $scope.ngaysx = "";
    $scope.hansd = "";
  };
  $scope.addphieunhapkho = function (id_PN, tenPN, id_SP, id_NV, soluong, gianhap, mangaynhap, hansd, ngaysx, id_NCC, id_LSP)//Lưu lúc thêm
  {
    if (tenPN == "" || id_SP == "" || id_NV == "" || soluong == "" || gianhap == "" ||
      mangaynhap == "" || hansd == "" || ngaysx == "" || id_NCC == "" || id_LSP == "") {
      alert("Vui lòng nhập những thông tin cần thiết!");
      $scope.save1 = "";//Nhập thiếu thì form không biến mất
      return;
    }
    else {
      $scope.save1 = "modal";//Nhập đủ thì lưu lại và form biến mất

      var data =
      {
        tenPN: tenPN,
        sanpham:
        {
          id_SP: id_SP,
          tenSP: "",
          nhacungcap:
          {
            id_NCC: "",
            tenNCC: "",
            sdtNCC: "",
            diachiNCC: "",
            stkNCC: ""
          },
          soluong_SP: "",
          giaban_SP: "",
          ngaynhap_ma: "",
          ngaysx_SP: "",
          hansd_SP: "",
          hinhanh_SP: "",
          phanloai: {
            id_LSP: "",
            tenLSP: "",
          }
        },
        nhanvien: {
          id_NV: id_NV,
          tenNV: "",
          gioitinhNV: "",
          chucvuNV: "",
          ngaysinhNV: "",
          cmndNV: "",
          quequanNV: "",
          stkNV: "",
          sdtNV: "",
          diachiNV: "",
        },
        nhacungcap: {
          id_NCC: id_NCC,
          tenNCC: "",
          sdtNCC: "",
          diachiNCC: "",
          stkNCC: "",
        },
        soluong: soluong,
        gianhap: gianhap,
        mangaynhap: mangaynhap,
        hansd: hansd,
        ngaysx: ngaysx,
        phanloai: {
          id_LSP: id_LSP,
          tenLSP: "",
        }
      }

      $http.post('http://localhost:8080/addphieunhapkho', JSON.stringify(data)).then(function (response) {
        if (response.data) {
          alert("Đã thêm mới ");
        }
      }, function (response) {
        $scope.bookTitles = response.statusText;
      });
    }
  };

  $scope.editphieunhapkho = function (phieunhapkho)//Khi bấm vào biểu tượng sửa
  {
    $scope.modalTitle = "CẬP NHẬT PHIẾU";//Thay đổi tiêu đề của modal
    $scope.adding = false;//Đang thêm = false
    $scope.modifying = true;//Đang sửa = true
    $scope.disabled = true;//disable id_ISBN không cho sửa

    $scope.id_PN = phieunhapkho.id_PN;
    $scope.tenPN = phieunhapkho.tenPN;
    document.getElementById("id_SP").value = phieunhapkho.sanpham.id_SP;
    document.getElementById("id_NV").value = phieunhapkho.nhanvien.id_NV;
    document.getElementById("id_NCC").value = phieunhapkho.nhacungcap.id_NCC;
    document.getElementById("id_LSP").value = phieunhapkho.phanloai.id_LSP;
    $scope.soluong = phieunhapkho.soluong;
    $scope.gianhap = phieunhapkho.gianhap;
    $scope.mangaynhap = new Date(phieunhapkho.mangaynhap);
    $scope.hansd = new Date(phieunhapkho.hansd);
    $scope.ngaysx = new Date(phieunhapkho.ngaysx);
  };


  //Sửa dữ liệu JSON ở server bằng phương thức $http.put
  $scope.updatephieunhapkho = function (id_PN, tenPN, id_SP, id_NV, soluong, gianhap, mangaynhap, hansd, ngaysx, id_NCC, id_LSP)//Lưu lúc sửa
  {

    $scope.save2 = "modal";//Nhập đủ thì lưu lại và form biến mất

    var data =
    {
      id_PN: id_PN,
      tenPN: tenPN,
      sanpham: {
        id_SP: document.getElementById("id_SP").value,
        tenSP: "",
        nhacungcap: {
          id_NCC: "",
          tenNCC: "",
          sdtNCC: "",
          diachiNCC: "",
          stkNCC: ""
        },
        soluong_SP: "",
        giaban_SP: "",
        ngaynhap_ma: "",
        ngaysx_SP: "",
        hansd_SP: "",
        hinhanh_SP: "",
        phanloai: {
          id_LSP: "",
          tenLSP: ""
        }
      },
      nhanvien: {
        id_NV: document.getElementById("id_NV").value,
        tenNV: "",
        gioitinhNV: "",
        chucvuNV: "",
        ngaysinhNV: "",
        cmndNV: "",
        quequanNV: "",
        stkNV: "",
        sdtNV: "",
        diachiNV: ""
      },
      nhacungcap: {
        id_NCC: document.getElementById("id_NCC").value,
        tenNCC: "",
        sdtNCC: "",
        diachiNCC: "",
        stkNCC: ""
      },
      soluong: soluong,
      gianhap: gianhap,
      mangaynhap: mangaynhap,
      hansd: hansd,
      ngaysx: ngaysx,
      phanloai: {
        id_LSP: document.getElementById("id_LSP").value,
        tenLSP: ""
      }
    };

    $http.put('http://localhost:8080/updatphieunhapkho/' + id_PN, JSON.stringify(data)).then(function (response) {
      if (response.data) {
        alert("Đã cập nhật !");
        location.reload();
      }
    }, function (response) {
      alert("Sửa thất bại!");
    });
  };

  //Xóa dữ liệu JSON ở server bằng phương thức $http.delete
  $scope.deletephieunhapkho = function (phieunhapkho)////Khi bấm vào nút DELETE
  {
    var id = phieunhapkho.id_PN;
    var r = confirm("Bạn Chắc Chắn Xóa ?");
    if (r == true) {
      $http({
        method: 'DELETE',
        url: 'http://localhost:8080/delphieunhapkho/' + id
      })
      alert("Xóa Thành Công");
      // window.location.reload();
    }
    else {
      alert("Xóa Thất Bại");
      // window.location.reload();
    }
  };

  $scope.chooseImg = function () {
    $scope.readyUpload = true;
  }

  $scope.updateImg = function (bookTitle) {
    var url = document.getElementById("imgUpdate").value;
    var fileName = url.substring(url.lastIndexOf("\\") + 1);

    var data =
    {
      id_ISBN: bookTitle.id_ISBN,
      id_LoaiSach: bookTitle.id_LoaiSach,
      publisher: {
        id_NXB: bookTitle.publisher.id_NXB,
        tenNXB: "",
        diaChiNXB: "",
        sdtNXB: "",
        emailNXB: ""
      },
      tenDS: bookTitle.tenDS,
      tomLuocNoiDung: bookTitle.tomLuocNoiDung,
      khoSach: bookTitle.khoSach,
      soTrang: bookTitle.soTrang,
      dinhKem: bookTitle.dinhKem,
      viTri: bookTitle.viTri,
      ngonNgu: bookTitle.ngonNgu,
      phienBan: bookTitle.phienBan,
      namXuatBan: bookTitle.namXuatBan,
      hinhAnh: fileName
    };

    $http.put('http://localhost:8080/updateBookTitle/' + bookTitle.id_ISBN, JSON.stringify(data)).then(function (response) {
      if (response.data) {
        alert("Đã cập nhật lại hình ảnh đầu sách " + bookTitle.id_ISBN + "!");
      }
    }, function (response) {
      alert("Lỗi cập nhật hình ảnh!");
    });
  }
});

myApp.controller('phieuxuatkhoCtrl', function ($scope, $http) {

  $http({
    method: "GET",
    url: "http://localhost:8080/phieuxuatkhos"
  }).then(function mySuccess(response) {
    $scope.phieuxuatkhos = response.data;
  }, function myError(response) {
    $scope.phieuxuatkhos = response.statusText;
  });

  $http({
    method: "GET",
    url: "http://localhost:8080/sanphams"
  }).then(function mySuccess(response) {
    $scope.sanphams = response.data;
  }, function myError(response) {
    $scope.sanphams = response.statusText;
  });

  $http({
    method: "GET",
    url: "http://localhost:8080/nhanviens"
  }).then(function mySuccess(response) {
    $scope.nhanviens = response.data;
  }, function myError(response) {
    $scope.nhanviens = response.statusText;
  });

  $http({
    method: "GET",
    url: "http://localhost:8080/khachhangs"
  }).then(function mySuccess(response) {
    $scope.khachhangs = response.data;
  }, function myError(response) {
    $scope.khachhangs = response.statusText;
  });

  $scope.themPhieuXuat = function ()//Khi bấm vào nút THÊM
  {
    let sanphamDto = {
      id_SP: "",
      soluong: "",
      giaban: ""
    }
    $scope.modalTitle = "THÊM PHIẾU XUẤT  ";//Thay đổi tiêu đề của modal
    $scope.adding = true;//Đang thêm = true
    $scope.modifying = false;//Đang sửa = false
    $scope.disabled = true;//enable id_ISBN
    $scope.phieuxuatkho = {
      id_PX: "",
      tenPX: "",
      sanphamsDto: [
        sanphamDto
      ],
      id_NV: "",
      id_KH: "",
      diachi: "",
      ngayxuat: ""
    }
  };

  $scope.themSanPham = function () {
    let sanphamDto = {
      id_SP: "",
      soluong: "",
      giaban: ""
    }
    $scope.phieuxuatkho.sanphamsDto.push(sanphamDto);
  }

  $scope.addphieuxuatkho = function (phieuxuatkho)//Lưu lúc thêm
  {
    if (phieuxuatkho.tenPX == "" || phieuxuatkho.sanphamsDto.length == 0 || phieuxuatkho.id_NV == "" || phieuxuatkho.id_KH == "" ||
      phieuxuatkho.diachi == "" || phieuxuatkho.ngayxuat == "") {
      alert("Vui lòng nhập những thông tin cần thiết!");
      $scope.save1 = "";//Nhập thiếu thì form không biến mất
      return;
    }
    else {
      $scope.save1 = "modal";//Nhập đủ thì lưu lại và form biến mất
      $http.post('http://localhost:8080/addphieuxuatkho', JSON.stringify(phieuxuatkho)).then(function (response) {
        if (response.data) {
          if (!phieuxuatkho.id_PX) {
            alert("Đã thêm mới ");
            location.reload();
          } else {
            alert("Đã cập nhập ");
            location.reload();
          }
        }
      }, function (response) {
        $scope.bookTitles = response.statusText;
      });
    }
  };

  $scope.editphieuxuatkho = function (phieuxuatkho)//Khi bấm vào biểu tượng sửa
  {
    $scope.modalTitle = "CẬP NHẬT PHIẾU";//Thay đổi tiêu đề của modal
    $scope.adding = false;//Đang thêm = false
    $scope.modifying = true;//Đang sửa = true
    $scope.disabled = true;//disable id_ISBN không cho sửa
    let phieuXuatKhoUpdate = new Object;
    phieuXuatKhoUpdate.id_PX = phieuxuatkho.id_PX;
    phieuXuatKhoUpdate.tenPX = phieuxuatkho.tenPX;
    phieuXuatKhoUpdate.sanphamsDto = phieuxuatkho.phieuXuatKhoSanPhams.
      map(x => ({
        id: x.id,
        id_SP: x.sanpham.id_SP.toString(),
        soluong: x.soluong,
        giaban: x.giaban
      }));
    phieuXuatKhoUpdate.id_NV = phieuxuatkho.nhanvien.id_NV.toString();
    phieuXuatKhoUpdate.id_KH = phieuxuatkho.khachhang.id_KH.toString();
    phieuXuatKhoUpdate.diachi = phieuxuatkho.diachi;
    phieuXuatKhoUpdate.ngayxuat = phieuxuatkho.ngayxuat;
    $scope.phieuxuatkho = phieuXuatKhoUpdate;
  };

  //Xóa dữ liệu JSON ở server bằng phương thức $http.delete
  $scope.deletephieuxuatkho = function (phieuxuatkho)////Khi bấm vào nút DELETE
  {
    var id = phieuxuatkho.id_PX;
    var r = confirm("Bạn Chắc Chắn Xóa ?");
    if (r == true) {
      $http({
        method: 'DELETE',
        url: 'http://localhost:8080/delphieuxuatkho/' + id
      })
      alert("Xóa Thành Công");
      // window.location.reload();
    }
    else {
      alert("Xóa Thất Bại");
      // window.location.reload();
    }
  };

  $scope.chooseImg = function () {
    $scope.readyUpload = true;
  }

  $scope.updateImg = function (bookTitle) {
    var url = document.getElementById("imgUpdate").value;
    var fileName = url.substring(url.lastIndexOf("\\") + 1);

    var data =
    {
      id_ISBN: bookTitle.id_ISBN,
      id_LoaiSach: bookTitle.id_LoaiSach,
      publisher: {
        id_NXB: bookTitle.publisher.id_NXB,
        tenNXB: "",
        diaChiNXB: "",
        sdtNXB: "",
        emailNXB: ""
      },
      tenDS: bookTitle.tenDS,
      tomLuocNoiDung: bookTitle.tomLuocNoiDung,
      khoSach: bookTitle.khoSach,
      soTrang: bookTitle.soTrang,
      dinhKem: bookTitle.dinhKem,
      viTri: bookTitle.viTri,
      ngonNgu: bookTitle.ngonNgu,
      phienBan: bookTitle.phienBan,
      namXuatBan: bookTitle.namXuatBan,
      hinhAnh: fileName
    };

    $http.put('http://localhost:8080/updateBookTitle/' + bookTitle.id_ISBN, JSON.stringify(data)).then(function (response) {
      if (response.data) {
        alert("Đã cập nhật lại hình ảnh đầu sách " + bookTitle.id_ISBN + "!");
      }
    }, function (response) {
      alert("Lỗi cập nhật hình ảnh!");
    });
  }
});
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Tạo ra directive mới để xác nhận khi xóa




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////----------Thống kê-------------

//Controller
