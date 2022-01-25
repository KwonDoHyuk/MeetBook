<template>
  <v-row>
    <!-- 영상 목록 -->
    <v-col class="col-12 col-md-9">
      <!-- 사용자 화면 -->
      <div>
        <v-card>현재 접속한 사용자 화면</v-card>
      </div>
      <!-- 주 진행자 화면 -->
      <div>
        <v-card>주 진행자 화면</v-card>
      </div>
      <!-- 기타 청취자 화면 -->
      <div>
        <v-card>다른 사용자 화면</v-card>
      </div>
      <p>order를 부여해서 배치하거나 세 줄로 나누어 배치하기 (주 진행자가 가운데에 오는 배치)</p>
    </v-col>


    <!-- 영상 외 (오른쪽 화면) -->
    <v-col class="col-12 col-md-3 d-flex flex-column">
      <!-- 채팅 화면 -->
      <div class="d-flex flex-column">
        <!-- 채팅창 -->
        <div>
          <v-card class="flex-grow-2">채팅창</v-card>
        </div>
        <!-- 채팅 폼 -->
        <div class="d-flex flex-row flex-grow-0">
          <v-textarea no-resize></v-textarea>
          <v-btn>보내기</v-btn>
        </div>
      </div>
      <!-- 기타 조작부 -->
      <v-divider class="my-5"></v-divider>
      <div class="d-flex flex-row justify-space-between">
        <v-btn>
          환경설정
        </v-btn>
        <v-btn color="accent">
          나가기
        </v-btn>
      </div>
    </v-col>


  </v-row>
</template>

<script>
export default {
  name: 'Mainscreen',
  components: {

  },

  props: {

  },

  data: function () {
    return {

    }
  },

}
</script>

<style>

</style>