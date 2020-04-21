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
</template>

<script>
export default {

    data() {
     return {

         response :[],
        
     }
    },
    created(){
          this.getDistancia();
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
                    "X-AIO-Key" : "aio_oPUI61fBuw2KPc0CoSSTafNQ2Qss"
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