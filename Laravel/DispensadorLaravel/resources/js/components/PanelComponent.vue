<template>
        <div class="container">
            <hr>
            <br>
            <button type="button" v-on:click="postOnGuzzle()" class="btn btn-primary btn-lg">Abrir croquetas</button>
            <button type="button" v-on:click="postOffGuzzle()" class="btn btn-secondary btn-lg">Cerrar croquetas</button>
            <button type="button" v-on:click="aguaOnGuzzle()" class="btn btn-primary btn-lg">Abrir agua</button>
            <button type="button" v-on:click="aguaOffGuzzle()" class="btn btn-secondary btn-lg">Cerrar agua</button>
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
        },

         postOnGuzzle(){
            axios({
                method: 'post',
                url: 'api/croquetasON'
               
                });   
        },

         postOffGuzzle(){
            axios({
                method: 'post',
                url: 'api/croquetasOFF'
                
               
                });   
        },

          aguaOnGuzzle(){
            axios({
                method: 'post',
                url: 'api/aguaON'
               
                });   
        },

         aguaOffGuzzle(){
            axios({
                method: 'post',
                url: 'api/aguaOFF'
                
               
                });   
        }

    }
    
}
</script>