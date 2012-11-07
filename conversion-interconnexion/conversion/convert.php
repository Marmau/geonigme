<?php
$fic=fopen("EspacesVerts_2011.csv", "r");
$monfichier = fopen('espacesVerts.rdf', 'r+');
fseek($monfichier, 0); // On remet le curseur au début du fichier
$j=1 ;//Compteur de ligne
	$rdf= '<rdf:RDF
    xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
    xmlns:espaceVert="http://localhost/espaceVert#"
    xmlns:geo="http://www.w3.org/2003/01/geo/wgs84_pos#" >';
	fputs($monfichier, $rdf);
while(!feof($fic))
{
	$line= fgets($fic,1024);
	if($j>1){
		$data=explode(';',$line);

		$rdf= '
	<rdf:Description rdf:about="http://localhost/espaceVert/'.$j.'">
			<espaceVert:nom>'.trim($data[5]).'</espaceVert:nom>
			<espaceVert:nombrePelouse>'.trim($data[16]).'</espaceVert:nombrePelouse>
			<espaceVert:nombreFleurs>'.trim($data[17]).'</espaceVert:nombreFleurs>
			<espaceVert:nombreArbustes>'.trim($data[18]).'</espaceVert:nombreArbustes>
			<espaceVert:nombreJeux>'.trim($data[19]).'</espaceVert:nombreJeux>
			';
			$vegetaux = explode(',',trim($data[27]));
			if(count($vegetaux)>1){
				for($i=0; $i<count($vegetaux); $i++){
					$rdf.= '	<espaceVert:vegetal>';
					$rdf.=trim($vegetaux[$i]);
					$rdf.= '</espaceVert:vegetal>
			';
					}

				}
					
			
		$vegetaux="";
		$rdf.= '<geo:lat>'.str_replace(",",".",trim($data[78])).'</geo:lat>
			<geo:long>'.str_replace(",",".",trim($data[77])).'</geo:long>
	</rdf:Description>';
		fputs($monfichier, $rdf);
		$rdf="";
	}
	$j ++;
	}
$rdf.=  '</rdf:RDF>';
fputs($monfichier, $rdf);
fclose($monfichier);
fclose($fic) ;

?> 

