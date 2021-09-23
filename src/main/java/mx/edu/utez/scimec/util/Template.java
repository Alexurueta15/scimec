package mx.edu.utez.scimec.util;

public class Template {

	public String contentTemplate(String nombre, String apellido, String pass, String correo) {
		String head ="<!DOCTYPE html>\r\n" +
				"<html lang=\"es\">\r\n" +
				"<head>\r\n" +
				"	<meta charset=\"utf-8\">\r\n" +
				"	<title>TEMPLATE</title>\r\n" +
				"	<link href=\"https://fonts.googleapis.com/css2?family=Lato:wght@400;900&display=swap\" rel=\"stylesheet\">\r\n" +
				"</head>\r\n" +
				"<body style=\"background-color: white \">\r\n" +
				"\r\n" +
				"\r\n" +
				"<table style=\"max-width: 600px; padding: 10px; margin:0 auto; border-collapse: collapse;\">";

		String header="<tr>\r\n" +
				"		<td style=\"background-color: #c9d468; text-align: center;display: flex;flex-direction: row; padding: 0\">\r\n" +
				"			<a style=\"flex: 0.4\"href=\"https://balanceneural-centro-de-medicina-integral.negocio.site/posts/3304692987552081839\">\r\n" +
				"				<img width=\"70%\" style=\"display:block; margin: 1.5% 3%\" src=\"https://cdn-s.acuityscheduling.com/appointmentType-thumb-3772683.png\">\r\n" +
				"			</a>\r\n" +
				"			<strong style=\"font-family: 'Lato', sans-serif; font-size: 30px; margin-top: 6%\">¡BIENVENIDO!</strong>\r\n" +
				"		</td>\r\n" +
				"	</tr>";

		String body="<tr>\r\n" +
				"		<td style=\"background-color: #ecf0f1\">\r\n" +
				"			<div style=\"color: #34495e; margin: 4% 10% 2%; text-align: justify;font-family: 'Lato',sans-serif;\">\r\n" +
				"				<h2 style=\"color: #e67e22; margin: 0 0 7px\">¡Hola "+nombre+" "+apellido+"!</h2>\r\n" +
				"				<p style=\"margin: 2px; font-size: 15px\">\r\n" +
				"					Es un gusto recibirte, a continuacion te proporcionaremos tu correo y contraseña para iniciar sesión en la aplicación de Balanceneural.\r\n" +
				"					<br><br>\r\n" +
				"					<strong>Correo: "+correo+"</strong><br><br>\r\n" +
				"					<strong>Contraseña: "+pass+"</strong></p>\r\n" +
				"				<div style=\"width: 100%; margin:40px 0; display: inline-block;text-align: center\">\r\n" +
				"					<a style=\"text-decoration: none; border-radius: 5px; padding: 11px 23px; color: white; background-color: #3498db\" href=\"http://localhost:3000/\">Ir a la aplicación</a>	\r\n" +
				"				</div>\r\n" +
				"\r\n" +
				"				<p style=\"color: #b3b3b3; font-size: 12px; text-align: center;margin: 30px 0 0\">Balanceneural Centro de Medicina Integral</p>\r\n" +
				"			</div>\r\n" +
				"		</td>\r\n" +
				"	</tr>";

		String footer="<tr>\r\n" +
				"		<td style=\"background-color: #c9d468; padding: 0;\" align=\"right\">\r\n" +
				"			<a href=\"https://balanceneural-centro-de-medicina-integral.negocio.site/posts/3304692987552081839\">\r\n" +
				"				<img width=\"5%\" style=\"margin: 1%\" src=\"https://cdn.icon-icons.com/icons2/1090/PNG/512/devices_78336.png\">\r\n" +
				"			</a>\r\n" +
				"			<a href=\"https://www.facebook.com/Balanceneural/?epa=SEARCH_BOX\">\r\n" +
				"				<img width=\"5%\" style=\"margin: 1%\" src=\"https://cdn.icon-icons.com/icons2/642/PNG/512/facebook_icon-icons.com_59205.png\">\r\n" +
				"			</a>\r\n" +
				"			\r\n" +
				"		</td>\r\n" +
				"	</tr>\r\n" +
				"</table>\r\n" +
				"\r\n" +
				"\r\n" +
				"</body>\r\n" +
				"</html>";


		return head+header+body+footer;
	}

	public String contentTemplateRecoverPassword(String pass, String correo) {
		String head ="<!DOCTYPE html>\r\n" +
				"<html lang=\"es\">\r\n" +
				"<head>\r\n" +
				"	<meta charset=\"utf-8\">\r\n" +
				"	<title>TEMPLATE</title>\r\n" +
				"	<link href=\"https://fonts.googleapis.com/css2?family=Lato:wght@400;900&display=swap\" rel=\"stylesheet\">\r\n" +
				"</head>\r\n" +
				"<body style=\"background-color: white \">\r\n" +
				"\r\n" +
				"\r\n" +
				"<table style=\"max-width: 600px; padding: 10px; margin:0 auto; border-collapse: collapse;\">";

		String header="<tr>\r\n" +
				"		<td style=\"background-color: #c9d468; text-align: center;display: flex;flex-direction: row; padding: 0\">\r\n" +
				"			<a style=\"flex: 0.4\"href=\"https://balanceneural-centro-de-medicina-integral.negocio.site/posts/3304692987552081839\">\r\n" +
				"				<img width=\"70%\" style=\"display:block;margin:auto;\" src=\"https://cdn-s.acuityscheduling.com/appointmentType-thumb-3772683.png\">\r\n" +
				"			</a>\r\n" +
				"		</td>\r\n" +
				"	</tr>";

		String body="<tr>\r\n" +
				"		<td style=\"background-color: #ecf0f1\">\r\n" +
				"			<div style=\"color: #34495e; margin: 4% 10% 2%; text-align: justify;font-family: 'Lato',sans-serif;\">\r\n" +
				"				<h2 style=\"color: #e67e22; margin: 0 0 7px\">¡Bienvenido de nuevo!</h2>\r\n" +
				"				<p style=\"margin: 2px; font-size: 15px\">\r\n" +
				"					Es un gusto notificarte que se recupero con éxito tu contraseña, a continuación te proporcionaremos tu correo y contraseña para iniciar sesión en la aplicación de Balanceneural.\r\n" +
				"					<br><br>\r\n" +
				"					<strong>Correo: "+correo+"</strong><br><br>\r\n" +
				"					<strong>Contraseña: "+pass+"</strong></p>\r\n" +
				"				<div style=\"width: 100%; margin:40px 0; display: inline-block;text-align: center\">\r\n" +
				"					<a style=\"text-decoration: none; border-radius: 5px; padding: 11px 23px; color: white; background-color: #3498db\" href=\"http://localhost:3000/\">Ir a la aplicación</a>	\r\n" +
				"				</div>\r\n" +
				"\r\n" +
				"				<p style=\"color: #b3b3b3; font-size: 12px; text-align: center;margin: 30px 0 0\">Balanceneural Centro de Medicina Integral</p>\r\n" +
				"			</div>\r\n" +
				"		</td>\r\n" +
				"	</tr>";

		String footer="<tr>\r\n" +
				"		<td style=\"background-color: #c9d468; padding: 0;\" align=\"right\">\r\n" +
				"			<a href=\"https://balanceneural-centro-de-medicina-integral.negocio.site/posts/3304692987552081839\">\r\n" +
				"				<img width=\"5%\" style=\"margin: 1%\" src=\"https://cdn.icon-icons.com/icons2/1090/PNG/512/devices_78336.png\">\r\n" +
				"			</a>\r\n" +
				"			<a href=\"https://www.facebook.com/Balanceneural/?epa=SEARCH_BOX\">\r\n" +
				"				<img width=\"5%\" style=\"margin: 1%\" src=\"https://cdn.icon-icons.com/icons2/642/PNG/512/facebook_icon-icons.com_59205.png\">\r\n" +
				"			</a>\r\n" +
				"			\r\n" +
				"		</td>\r\n" +
				"	</tr>\r\n" +
				"</table>\r\n" +
				"\r\n" +
				"\r\n" +
				"</body>\r\n" +
				"</html>";


		return head+header+body+footer;
	}

	public String contentTemplateDateInformation(String nombre, String apellido,String fecha, String servicio) {
		String head ="<!DOCTYPE html>\r\n" +
				"<html lang=\"es\">\r\n" +
				"<head>\r\n" +
				"	<meta charset=\"utf-8\">\r\n" +
				"	<title>TEMPLATE</title>\r\n" +
				"	<link href=\"https://fonts.googleapis.com/css2?family=Lato:wght@400;900&display=swap\" rel=\"stylesheet\">\r\n" +
				"</head>\r\n" +
				"<body style=\"background-color: white \">\r\n" +
				"\r\n" +
				"\r\n" +
				"<table style=\"max-width: 600px; padding: 10px; margin:0 auto; border-collapse: collapse;\">";

		String header="<tr>\r\n" +
				"		<td style=\"background-color: #c9d468; text-align: center;display: flex;flex-direction: row; padding: 0\">\r\n" +
				"			<a style=\"flex: 0.4\"href=\"https://balanceneural-centro-de-medicina-integral.negocio.site/posts/3304692987552081839\">\r\n" +
				"				<img width=\"70%\" style=\"display:block; margin: 1.5% 3%\" src=\"https://cdn-s.acuityscheduling.com/appointmentType-thumb-3772683.png\">\r\n" +
				"			</a>\r\n" +
				"			<strong style=\"font-family: 'Lato', sans-serif; font-size: 30px; margin-top: 6%\">¡LO SENTIMOS!</strong>\r\n" +
				"		</td>\r\n" +
				"	</tr>";

		String body="<tr>\r\n" +
				"		<td style=\"background-color: #ecf0f1\">\r\n" +
				"			<div style=\"color: #34495e; margin: 4% 10% 2%; text-align: justify;font-family: 'Lato',sans-serif;\">\r\n" +
				"				<h2 style=\"color: #e67e22; margin: 0 0 7px\">¡Hola "+nombre+" "+apellido+"!</h2>\r\n" +
				"				<p style=\"margin: 2px; font-size: 15px\">\r\n" +
				"					Te notificamos que se ha rechazado tu cita solicitida, intenta agendar nuevamente.\r\n" +
				"					<br><br>\r\n" +
				"					<strong>Fecha de la cita: "+fecha+"</strong><br><br>\r\n" +
				"					<strong>Servicio: "+servicio+"</strong></p>\r\n" +
				"				<div style=\"width: 100%; margin:40px 0; display: inline-block;text-align: center\">\r\n" +
				"					<a style=\"text-decoration: none; border-radius: 5px; padding: 11px 23px; color: white; background-color: #3498db\" href=\"http://localhost:3000/\">Ir a la aplicación</a>	\r\n" +
				"				</div>\r\n" +
				"\r\n" +
				"				<p style=\"color: #b3b3b3; font-size: 12px; text-align: center;margin: 30px 0 0\">Balanceneural Centro de Medicina Integral</p>\r\n" +
				"			</div>\r\n" +
				"		</td>\r\n" +
				"	</tr>";

		String footer="<tr>\r\n" +
				"		<td style=\"background-color: #c9d468; padding: 0;\" align=\"right\">\r\n" +
				"			<a href=\"https://balanceneural-centro-de-medicina-integral.negocio.site/posts/3304692987552081839\">\r\n" +
				"				<img width=\"5%\" style=\"margin: 1%\" src=\"https://cdn.icon-icons.com/icons2/1090/PNG/512/devices_78336.png\">\r\n" +
				"			</a>\r\n" +
				"			<a href=\"https://www.facebook.com/Balanceneural/?epa=SEARCH_BOX\">\r\n" +
				"				<img width=\"5%\" style=\"margin: 1%\" src=\"https://cdn.icon-icons.com/icons2/642/PNG/512/facebook_icon-icons.com_59205.png\">\r\n" +
				"			</a>\r\n" +
				"			\r\n" +
				"		</td>\r\n" +
				"	</tr>\r\n" +
				"</table>\r\n" +
				"\r\n" +
				"\r\n" +
				"</body>\r\n" +
				"</html>";


		return head+header+body+footer;
	}
}
