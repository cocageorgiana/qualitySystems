<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
$curl = curl_init();
curl_setopt($curl, CURLOPT_CUSTOMREQUEST, "GET");
//curl_setopt($curl, CURLOPT_POSTFIELDS, $data);
curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
/*curl_setopt($curl, CURLOPT_HTTPHEADER, array(
    'Content-Type: application/json',
    'Content-Length: ' . strlen($data))
);*/
curl_setopt($curl, CURLOPT_URL, 'https://quality.cfapps.io/middleware/results');
$result = curl_exec($curl);
echo $result;
/*
$json    = json_decode($result,true);
curl_close($curl);
print_r($json['word']);
echo "\n";
*/
