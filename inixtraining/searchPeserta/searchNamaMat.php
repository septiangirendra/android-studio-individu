<?php 
	$nama_mat = $_GET['nama_mat'];
	
	//Importing database
	require_once('../koneksi.php');
	
	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	$sql = "SELECT m.nama_mat materi, P.nama_pst peserta
		FROM `tb_detail_kelas` DK
		JOIN tb_peserta P 
		ON DK.id_pst = P.id_pst
		JOIN tb_kelas K
		ON DK.id_kls = K.id_kls
		JOIN tb_materi M 
		ON m.id_mat = k.id_mat
		WHERE m.nama_mat LIKE '%$nama_mat%'
		ORDER BY m.nama_mat";
	
	//Mendapatkan Hasil 
	$r = mysqli_query($con,$sql);
	
	//Memasukkan Hasil Kedalam Array
	$result = array();

	while($row = mysqli_fetch_array($r)){
	array_push($result,array(
			"materi"=>$row['materi'],
			"peserta"=>$row['peserta']
		));
	}

	//Menampilkan dalam format JSON
	echo json_encode(array('result'=>$result));

	
	mysqli_close($con);
?>