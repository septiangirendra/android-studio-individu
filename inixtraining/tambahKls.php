<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Mendapatkan Nilai Variable
		$tgl_mulai = $_POST['tgl_mulai'];
		$tgl_selesai = $_POST['tgl_mulai'];
		$id_ins = $_POST['id_ins'];
		$id_mat = $_POST['id_mat'];		

		//Pembuatan Syntax SQL
		$sql = "INSERT INTO tb_kelas (tgl_mulai_kls, tgl_akhir_kls, id_ins, id_mat) 
		VALUES ('$tgl_mulai','$tgl_selesai','$id_ins', '$id_mat')";
		
		//Import File Koneksi database
		require_once('koneksi.php');
		
		//Eksekusi Query database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Menambahkan';
		}else{
			echo 'Gagal Menambahkan';
		}
		
		mysqli_close($con);
	}
?>