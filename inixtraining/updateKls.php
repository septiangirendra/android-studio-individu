<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//Mendapatkan Nilai Dari Variable 
		$id_kls = $_POST['id_kls'];
		$tgl_mulai = $_POST['tgl_mulai'];
		$tgl_selesai = $_POST['tgl_selesai'];
		$id_ins = $_POST['id_ins'];
		$id_mat = $_POST['id_mat'];
		
		//import file koneksi database 
		require_once('koneksi.php');
		
		//Membuat SQL Query
		$sql = "UPDATE tb_kelas SET tgl_mulai_kls = '$tgl_mulai',
		tgl_akhir_kls = '$tgl_selesai', id_ins ='$id_ins',
		id_mat = '$id_mat' WHERE id_kls = $id_kls";
		
		//Meng-update Database 
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Update';
		}else{
			echo 'Gagal Update';
		}
		
		mysqli_close($con);
	}
?>