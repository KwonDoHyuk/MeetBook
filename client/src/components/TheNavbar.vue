<template>
  <v-app-bar
    app>
    <v-toolbar-title>Meetbook</v-toolbar-title>
    <v-btn
        href="#"
        target="_blank"
        text
      >
      <span class="mr-2">모임</span>
    </v-btn>
    <v-spacer></v-spacer>

    <!-- 검색 -->
    <v-text-field
      rounded
      solo
      flat>
    </v-text-field>
    <v-btn icon>
      <v-icon>mdi-magnify</v-icon>
    </v-btn>

    <div v-if="login" class="navbar-menu-loggedin">
      <v-btn
          href="#"
          target="_blank"
          text
        >
        <span class="mr-2">모임 개설</span>
      </v-btn>
      <v-btn
          href="#"
          target="_blank"
          text
        >
        <span class="mr-2">나의 일정</span>
      </v-btn>

    </div>
    <div v-else class="navbar-menu-not-loggedin">
      <v-btn
          href="#"
          target="_blank"
          text
        >
        <span class="mr-2">로그인</span>
      </v-btn>
      <v-btn
          href="#"
          target="_blank"
          text
        >
        <span class="mr-2">회원가입</span>
      </v-btn>
    </div>

    <!-- 계정 아이콘 -->
    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs}">
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
          v-for="(item, index) in profileMenuItems" :key="index">
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>

  </v-app-bar>
</template>

<script>
export default {
  name: "navbar",
  data: function () {
    return {
      login: this.$store.state.login,
      profileMenuItems: [
        { title: '내 프로필'},
        { title: '계정 설정'},
        { title: '로그아웃'},
      ],
    }
  },
}
</script>

<style>

</style>