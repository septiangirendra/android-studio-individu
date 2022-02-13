<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Mendapatkan Nilai Variable
		$nama_pst = $_POST['nama_pst'];
		$email_pst = $_POST['email_pst'];
		$hp_pst = $_POST['hp_pst'];

		//Pembuatan Syntax SQL
		$sql = "INSERT INTO tb_peserta (nama_pst, email_pst, hp_pst) 
		VALUES ('$nama_pst','$email_pst','$hp_pst')";
		
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