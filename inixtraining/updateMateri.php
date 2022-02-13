<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//Mendapatkan Nilai Dari Variable 
		$id = $_POST['id_mat'];
		$nama_mat = $_POST['nama_mat'];

		
		//import file koneksi database 
		require_once('koneksi.php');
		
		//Membuat SQL Query
		$sql = "UPDATE tb_materi SET nama_mat = '$nama_mat'WHERE id_mat = $id;";
		
		//Meng-update Database 
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Update';
		}else{
			echo 'Gagal Update';
		}
		
		mysqli_close($con);
	}
?>