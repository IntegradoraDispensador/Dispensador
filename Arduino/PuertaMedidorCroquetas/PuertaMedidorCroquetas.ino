#include <Servo.h>
#include <ESP8266WiFi.h>
#include "Adafruit_MQTT.h"
#include "Adafruit_MQTT_Client.h"


#define WLAN_SSID       "MEGACABLE393"
#define WLAN_PASS       "canela200100#"
#define AIO_SERVER      "io.adafruit.com"
#define AIO_SERVERPORT  1883                   
#define AIO_USERNAME    "ubaldo01"
#define AIO_KEY         "aio_RXta707MSUJTpua5Ygi3bJ2vBYeB"

WiFiClient client;

Adafruit_MQTT_Client mqtt(&client, AIO_SERVER, AIO_SERVERPORT, AIO_USERNAME, AIO_USERNAME, AIO_KEY);
Adafruit_MQTT_Subscribe onoffbutton = Adafruit_MQTT_Subscribe(&mqtt, AIO_USERNAME "/feeds/onoff");
Adafruit_MQTT_Publish distValue = Adafruit_MQTT_Publish(&mqtt, AIO_USERNAME "/feeds/ultrasonico");

void ConexionMQQT();

Servo ser1;
uint16_t valorDistancia = 0;
long tiempoUltimaLectura=0;
const int Trigger = 5;  
const int Echo = 4; 
unsigned long previousMillis = 0;

void setup() 
{
  Serial.begin(115200);
  
  ser1.attach(16);
 
  pinMode(Trigger, OUTPUT); 
  pinMode(Echo, INPUT);  
  digitalWrite(Trigger, LOW);
  delay(10);

  Serial.println(F("Adafruit / PROYECTO"));

  Serial.println(); Serial.println();
  Serial.print("Conectando a ");
  Serial.println(WLAN_SSID);

  WiFi.begin(WLAN_SSID, WLAN_PASS);
  while (WiFi.status() != WL_CONNECTED) 
  {
    delay(500);
    Serial.print(".");
  }
  Serial.println();

  Serial.println("WiFi Conectado con exito");
  Serial.print("Su dirreción IP address: "); Serial.println(WiFi.localIP());

  mqtt.subscribe(&onoffbutton);
}

void loop() 
{

   ConexionMQQT();

  Adafruit_MQTT_Subscribe *subscription;

  
     if(millis()-tiempoUltimaLectura>1000)
     {
        valorDistancia=distancia(); 
  
        if (! distValue.publish(valorDistancia)) 
        {
          Serial.println(F("Failed Distancia"));
        }  
        if(valorDistancia >= 17)
        {
          Serial.print("El valor de la distancia es: ");
          Serial.print(valorDistancia);
          Serial.print("cm");
          Serial.println(" Recipiente vacío");
        }
        else
        {
          Serial.print("El valor de la distancia es: ");
          Serial.print(valorDistancia);
          Serial.print("cm");
          Serial.println(" Recipiente lleno");
        }
       tiempoUltimaLectura=millis();
    }
    
   subscription = mqtt.readSubscription(5000);
   
    if (subscription == &onoffbutton) 
    {
      Serial.print(F("On-Off button: "));
      Serial.println((char *)onoffbutton.lastread);
      
        if (strcmp((char *)onoffbutton.lastread, "ON") == 0) 
          {     
            ser1.write(120);
            Serial.println("120° grados");
            Serial.println("Puerta abierta");
          }
        if (strcmp((char *)onoffbutton.lastread, "OFF") == 0) 
          {     
            ser1.write(0);
            Serial.println("0° grados");
            Serial.println("Puerta cerrada");
          }
    }  

  // por si el ping esta alto desconecte 
  //if(! mqtt.ping()) {
   // mqtt.disconnect();
  //}
}

void ConexionMQQT() 
{
  int8_t ret;

  if (mqtt.connected()) 
  {
    return;
  }

  Serial.println("Conectando a MQTT... Espera compa");

  uint8_t retries = 3;
  while ((ret = mqtt.connect()) != 0) 
  { 
       Serial.println(mqtt.connectErrorString(ret));
       Serial.println("Reintentando conexión MQTT ...");
       mqtt.disconnect();
       delay(5000);  
       retries--;
       if (retries == 0) 
       {
         while (1);
       }
  }
  Serial.println("MQTT conectado con exito!");
}


long distancia()
{
  long t; //timepo que demora en llegar el eco
  long d; //distancia en centimetros

  digitalWrite(Trigger, HIGH);
  delayMicroseconds(10);          //Enviamos un pulso de 10us
  digitalWrite(Trigger, LOW);
  t = pulseIn(Echo, HIGH); //obtenemos el ancho del pulso
  d = t/59;             //escalamos el tiempo a una distancia en cm
  return d;  
}
