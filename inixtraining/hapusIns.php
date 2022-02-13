<?php 
 //Mendapatkan Nilai ID
 $id = $_GET['id'];
 
 //Import File Koneksi Database
 require_once('koneksi.php');
 
 //Membuat SQL Query
 $sql = "DELETE FROM tb_instruktur WHERE id_ins=$id;";

 
 //Menghapus Nilai pada Database 
 if(mysqli_query($con,$sql)){
 echo 'Berhasil Menghapus';
 }else{
 echo 'Gagal Menghapus';
 }
 
 mysqli_close($con);
 ?>