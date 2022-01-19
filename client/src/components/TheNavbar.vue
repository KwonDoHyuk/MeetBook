<template>
  <v-app-bar app color="#FAF6EA">
    <v-toolbar-title>Meetbook</v-toolbar-title>
    <v-btn text plain to="/conf/list">
      <span class="mr-2">모임</span>
    </v-btn>

    <v-spacer></v-spacer>

    <!-- 검색 -->
    <v-text-field
      solo dense flat
      placeholder="검색"
      append-outer-icon="mdi-magnify"
      hide-details="true"
      style="max-width: 450px;"
      v-model="keyword"
      @keyup.enter="searchKeyword"
      >
    </v-text-field>

    <v-spacer></v-spacer>

    <div v-if="login" class="navbar-menu-loggedin">
      <v-btn
          to="/conf/create"
          text
          plain
        >
        <span class="mr-2">모임 개설</span>
      </v-btn>
      <v-btn
          to="/conf/schedule"
          text
          plain
        >
        <span class="mr-2">나의 일정</span>
      </v-btn>

    </div>
    <div v-else class="navbar-menu-not-loggedin">
      <v-btn
          to="/login"
          text
          plain
        >
        <span class="mr-2">로그인</span>
      </v-btn>
      <v-btn
          to="/signup"
          text
          plain
        >
        <span class="mr-2">회원가입</span>
      </v-btn>
    </div>

    <!-- 계정 아이콘 -->
    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          icon
          v-bind="attrs"
          v-on="on">
          <v-avatar>
            <v-icon>
              mdi-account-circle
            </v-icon>
          </v-avatar>
        </v-btn>

      </template>

      <v-list>
        <v-list-item
          v-for="(item, index) in profileMenuItems" :key="index" :to="item.to">
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item>

        <!-- router 대신 다른 작업을 해야 하는 로그아웃만 따로 떼어서 작성 -->
        <v-list-item @click="logout">
          <v-list-item-title>로그아웃</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>

  </v-app-bar>
</template>

<script>
// import axios from 'axios'
import { mapGetters } from 'vuex'
export default {
  name: "navbar",
  data: function () {
    return {
      login: this.$store.state.login,
      profileMenuItems: [
        { title: '내 프로필', to:'/profile',},
        { title: '계정 설정', to:'/accounts/settings',},
      ],
      keyword: '',
    }
  },

  methods: {
    // 로그아웃
    logout: function () {
      // localStorage.removeItem('토큰')
      // this.$$store.dispatch('로그아웃 관련 Vuex Method')
      // this.$router.push({name: 'Home'}) // 홈으로 보내기
      console.log('로그아웃')
    },

    // 검색
    searchKeyword: function () {
      let q = this.keyword
      console.log(q)
      // axios({
      //   method: 'get',
      //   url: ''
      // })
    }
  },

  computed: {
    // 로그인 여부 확인
    ...mapGetters(['isLogin',])
  },
}
</script>

<style>

</style>