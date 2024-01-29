/** @type {import('tailwindcss').Config} */
module.exports = {
  mode: process.env.NODE_ENV ? 'jit' : undefined,
  content: [
    "./src/**/*.html",
    "./src/**/*.js",
  ],
  safelist: {
    deep: [/^dark:/],
  },
  theme: {
    extend: {
      fontFamily: {
        header: ['Montserrat', 'sans-serif'],
        body: ['Roboto', 'sans-serif'],
        accent: ['Lato', 'sans-serif'],
        logo: ['Exo', 'sans-serif'],
        secondary: ['"Open Sans"', 'sans-serif'],
        call_to_action: ['Raleway', 'sans-serif'],
      },
    },
  },
  variants: {
    extends: {},
  },
  plugins: [],
};

