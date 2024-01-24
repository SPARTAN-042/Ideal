/** @type {import('tailwindcss').Config} */
module.exports = {
  mode: process.env.NODE_ENV ? 'jit' : undefined,
  content: [
    "./src/**/*.{html,js}",
  ],
  safelist: {
    deep: [/^dark:/],
  },
  theme: {
    extend: {},
  },
  variants: {
    extends: {},
  },
  plugins: [],
};

