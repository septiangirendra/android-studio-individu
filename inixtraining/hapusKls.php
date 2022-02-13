<?php 
 //Mendapatkan Nilai ID
 $id = $_GET['id'];
 
 //Import File Koneksi Database
 require_once('koneksi.php');
 
 //Membuat SQL Query
 $sql = "DELETE FROM tb_kelas WHERE id_kls=$id;";

 
 //Menghapus Nilai pada Database 
 if(mysqli_query($con,$sql)){
 echo 'Berhasil Menghapus';
 }else{
 echo 'Gagal Menghapus';
 }
 
 mysqli_close($con);
 ?>