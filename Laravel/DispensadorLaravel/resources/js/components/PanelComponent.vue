<template>
        <div class="container">
            <hr>
            <br>
            <button type="button" v-on:click="postOn()" class="btn btn-primary btn-lg">Abrir croquetas</button>
            <button type="button" v-on:click="postOff()" class="btn btn-secondary btn-lg">Cerrar croquetas</button>
            <button type="button" v-on:click="aguaOn()" class="btn btn-primary btn-lg">Abrir agua</button>
            <button type="button" v-on:click="aguaOff()" class="btn btn-secondary btn-lg">Cerrar agua</button>
            <br>
            <hr>

            <div style="height: 800px; overflow-y: scroll">
            <table class="table table-dark">
            <thead>
                <tr>
                
                <th scope="col">Distancia en cm</th>
                <th scope="col">Fecha de Registro</th>
                
                </tr>
            </thead>
            <tbody>
                <tr v-for="response in response.data" :key="response.id">
                <td>{{response.value}}</td>
                <td>{{response.created_at}}</td>
                
                </tr>
            
                
                </tbody>
                </table>
                </div>


      
        </div>
</template>

<script>
export default {

    data() {
     return {

         response :[],
        
     }
    },
    created(){
          this.getUltrasonico();
        },


    methods: {

        postOn(){
            axios({
                method: 'post',
                url: 'https://io.adafruit.com/api/v2/ubaldo01/feeds/onoff/data',
                 params: {
                    "X-AIO-Key" : "aio_pHjQ04DaljjLSM6O4dgdWzXO7jNj"
                   
                        },
                data: {
                    "value" : 'ON',
                    
                    }
                });   
        },

         postOff(){
            axios({
                method: 'post',
                url: 'https://io.adafruit.com/api/v2/ubaldo01/feeds/onoff/data',
                params: {
                    "X-AIO-Key" : "aio_pHjQ04DaljjLSM6O4dgdWzXO7jNj"
                        },
                data: {
                    value: 'OFF',
                    
                    }
                });   
        },

         aguaOn(){
            axios({
                method: 'post',
                url: 'https://io.adafruit.com/api/v2/GerardoDM/feeds/agua/data',
                params: {
                    "X-AIO-Key" : "aio_EaMk439i6cotrfXp0Prmaev7ncKa"
                        },
                data: {
                    value: 'ON',
                    
                    }
                });   
        },

         aguaOff(){
            axios({
                method: 'post',
                url: 'https://io.adafruit.com/api/v2/GerardoDM/feeds/agua/data',
                params: {
                    "X-AIO-Key" : "aio_EaMk439i6cotrfXp0Prmaev7ncKa"
                        },
                data: {
                    value: 'OFF',
                    
                    }
                });   
        },

        getDistancia(){

             let self = this;
            axios({
                method: 'get',
                url: 'https://io.adafruit.com/api/v2/ubaldo01/feeds/ultrasonico/data',
                 params: {
                    "X-AIO-Key" : "aio_TAPe68HndORlKq8E1Iv20EDtNLOS",
                    "limit" : 50

                     
                        },
                })
                .then(function (response) {

                     self.response = response;
                    console.log(response.data)
                });
        },

         getUltrasonico(){

             let self = this;
            axios({
                method: 'get',
                url: 'api/ultrasonico',
                 params: {
                 
                    "limit" : 50

                     
                        },
                })
                .then(function (response) {

                     self.response = response;
                    console.log(response.data)
                });
        }

    }
    
}
</script>