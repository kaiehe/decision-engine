   <template>
     <div class="loan-application">
       <h1>Loan Application</h1>
       <div v-if="applicationData">
         <h2>{{ applicationData.approvedLoanAmount }}</h2>
         <h3>{{ applicationData.approvedLoanPeriod }}</h3>
         <p>Period: {{ decisionData.period }}</p>
       </div>
     </div>
   </template>
   <script>
   export default {
     data() {
       return {
         applicationData: null,
       };
     },
     mounted() {
        axios
            .get('/api')
            .then((response) => {
              this.applicationData = response.data;
            })
            .catch((error) => {
              console.error('Error fetching loan application data:', error);
            });
     },
   };
   </script>