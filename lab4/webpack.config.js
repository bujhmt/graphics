const path = require('path');

module.exports = {
    entry: {
        app: './src/index.ts'
    },
    watchOptions: {
        ignored: /node_modules/,
    },
    output: {
        filename: 'main.js',
        path: path.resolve(__dirname, 'build'),
    },
    resolve: {
        extensions: ['.ts', '.js'],
    },
    module: {
        rules: [
            {
                test: /\.ts$/,
                use: 'ts-loader',
                exclude: /node_modules/,
            }
        ],
    },
};
