<?php 
	//Mendapatkan Nilai Dari Variable ID Pegawai yang ingin ditampilkan
	$id = $_GET['id'];
	
	//Importing database
	require_once('koneksi.php');
	
	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	$sql = "SELECT DK.id_detail_kls id_detail_kls, DK.id_kls id_kls, DK.id_pst id_pst, I.nama_ins nama_ins, M.nama_mat nama_mat, P.nama_pst nama_pst,
			DK.id_pst id_pst
			FROM tb_detail_kelas DK
			JOIN tb_kelas K
			ON DK.id_kls = K.id_kls
			JOIN tb_materi M
			ON M.id_mat = K.id_mat
			JOIN tb_instruktur I
			ON I.id_ins = K.id_ins
			JOIN tb_peserta P
			ON P.id_pst = DK.id_pst WHERE DK.id_kls=$id
			GROUP BY  id_kls, id_pst";
	
	//Mendapatkan Hasil 
	$r = mysqli_query($con,$sql)or die( mysqli_error($con));
	
	//Memasukkan Hasil Kedalam Array
	$result = array();

	while($row = mysqli_fetch_array($r)){
	array_push($result,array(
			"id_kls"=>$row['id_kls'],
			"id_detail_kls"=>$row['id_detail_kls'],
			"nama_ins"=>$row['nama_ins'],
			"nama_mat"=>$row['nama_mat'],
            		"nama_pst"=>$row['nama_pst'],
			"id_pst"=>$row['id_pst']
		));
	}
	//Menampilkan dalam format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>