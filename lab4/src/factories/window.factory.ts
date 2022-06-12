import {BoxBufferGeometry, DoubleSide, Group, Mesh, MeshBasicMaterial, MeshLambertMaterial, PlaneGeometry} from 'three';

export function WindowFactory(): Group {
    const window = new Group();

    const main = new Mesh(
        new PlaneGeometry(70, 70),
        new MeshBasicMaterial( {color: 0xfffff0, side: DoubleSide} )
    );

    const frame1 = new Mesh(
        new BoxBufferGeometry(2, 70, 1),
        new MeshBasicMaterial({color: 0x000000}),
    )

    const frame2 = new Mesh(
        new BoxBufferGeometry(2, 70, 1),
        new MeshBasicMaterial({color: 0x000000}),
    )

    frame2.rotateZ(-7.850);

    window.add(
        main,
        frame1,
        frame2,
    );
    return window;
}
